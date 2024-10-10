package willow.train.kuayue.block.food.effect;

import com.google.common.collect.Lists;
import com.mojang.datafixers.util.Pair;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffectUtil;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.ItemStack;

import java.util.List;
import java.util.Map;

/**
 * MIT License
 *
 * Copyright (c) 2020 vectorwing
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

public class EffectUtil {

    /**
     *  This method is based on TextUtils.addFoodEffectTooltip in FarmersDelight.
     *  Credits to Lucas Barcellos (FarmersDelight) for the implementation reference!
     *  https://www.curseforge.com/minecraft/mc-mods/farmers-delight
     */
    // 物品栏显示食物效果及持续时间
    public static void addFoodEffectToTooltip(ItemStack itemStack, List<Component> tooltip) {
        // 获取物品栈中对应的食物参数
        FoodProperties foodProperties = itemStack.getItem().getFoodProperties();
        if (foodProperties == null) {
            return;
        }
        // 添加食物回复量
        tooltip.add(Component.translatable("item.kuayue.tooltip.restores_health",
                foodProperties.getNutrition()).withStyle(ChatFormatting.DARK_GREEN));
        tooltip.add(Component.translatable("item.kuayue.tooltip.restores_saturation",
                foodProperties.getSaturationModifier()).withStyle(ChatFormatting.DARK_GREEN));

        // 获取食物效果列表
        List<Pair<MobEffectInstance, Float>> effects = foodProperties.getEffects();
        // 新建一个属性列表（Pair类型的list集合）
        List<Pair<Attribute, AttributeModifier>> attributeList = Lists.newArrayList();
        if (effects.isEmpty()) {
            return;
        }
        // 遍历食物效果
        for (Pair<MobEffectInstance, Float> effect : effects) {
            // 获得食物效果Pair的第一个元素mobEffectInstance，其包含了效果对象，持续时间，效果等级等属性。
            // （第二元素即为触发该效果的概率，最大值为1，即百分之百。）
            MobEffectInstance mobEffectInstance = effect.getFirst();
            // 从第一元素中获取效果对象（类型为MobEffect），其中包含了:
            // 属性修改器集合（attributeModifiers），效果类型（category = "BENEFICIAL"），
            // 效果颜色（color），类型id（descriptionId = "effect.minecraft.regeneration"）等属性。
            MobEffect mobEffect = mobEffectInstance.getEffect();
            // 从效果对象中获取属性修改器集合
            Map<Attribute, AttributeModifier> attributeModifiers = mobEffect.getAttributeModifiers();

            // 先将效果类型id添加到可变文本输出组件对象（类型为MutableComponent）中
            MutableComponent translation = Component.translatable(mobEffectInstance.getDescriptionId());
            // 若属性修改器集合不为空
            if (!attributeModifiers.isEmpty()) {
                // 遍历属性修改器集合，集合中每个元素的key包含Attribute类型的descriptionId，
                // value则包含了UUID，operation，name，属性提升幅度（amount）等属性。
                for (Map.Entry<Attribute, AttributeModifier> entry : attributeModifiers.entrySet()) {
                    // 获取属性修改器键值对的value，类型为AttributeModifier。
                    AttributeModifier rawModifier = entry.getValue();
                    // 根据value中的参数创建一个新的AttributeModifier对象
                    AttributeModifier modifier =
                            new AttributeModifier(
                                    rawModifier.getName(),
                                    mobEffect.getAttributeModifierValue(mobEffectInstance.getAmplifier(), rawModifier),
                                    rawModifier.getOperation());
                    // 以属性修改器的key为键，以新AttributeModifier对象为值，组装为一个Pair对象并添加到新的属性列表attributeList中。
                    attributeList.add(new Pair<>(entry.getKey(), modifier));
                }
            }
            // 若效果等级大于0（游戏中为大于I级）
            if (mobEffectInstance.getAmplifier() > 0) {
                translation = Component.translatable("potion.withAmplifier",
                        translation, Component.translatable("potion.potency." + mobEffectInstance.getAmplifier()));
            }
            // 若持续时间大于1秒
            if (mobEffectInstance.getDuration() > 20) {
                // 将potion.withDuration作为key，再将其他文本作为args注入到可变文本输出组件对象。
                translation = Component.translatable("potion.withDuration",
                        translation, MobEffectUtil.formatDuration(mobEffectInstance, 1.0F));
            }
            // translatable = Component.translatable(translatable, );
            // 将文本添加到工具栏
            tooltip.add(translation.withStyle(mobEffect.getCategory().getTooltipFormatting()));
            // 火车盒饭的文本内容:
            // translation{key='potion.withDuration',
            // args=[translation{key='effect.minecraft.regeneration', args=[]}, 0:05]}
        }

        // 若新建属性列表不为空
        if (!attributeList.isEmpty()) {
            tooltip.add(CommonComponents.EMPTY);
            // 添加紫色文本：当生效后
            tooltip.add((Component.translatable("potion.whenDrank")).withStyle(ChatFormatting.DARK_PURPLE));
            // 遍历属性列表
            for (Pair<Attribute, AttributeModifier> pair : attributeList) {
                // 获取属性value
                AttributeModifier modifier = pair.getSecond();
                // 获取属性提升幅度
                double amount = modifier.getAmount();
                // 定义格式化的提升幅度
                double formattedAmount;
                if (modifier.getOperation() != AttributeModifier.Operation.MULTIPLY_BASE && modifier.getOperation() != AttributeModifier.Operation.MULTIPLY_TOTAL) {
                    formattedAmount = modifier.getAmount();
                } else {
                    // 若value的operation属性为MULTIPLY_BASE或MULTIPLY_TOTAL，则将幅度乘以100。
                    formattedAmount = modifier.getAmount() * 100.0D;
                }
                // 如果是提升某项属性值
                if (amount > 0.0D) {
                    // 添加蓝色文本
                    tooltip.add((Component.translatable(
                            "attribute.modifier.plus." + modifier.getOperation().toValue(),         // +
                            ItemStack.ATTRIBUTE_MODIFIER_FORMAT.format(formattedAmount),    // 20%
                            Component.translatable(pair.getFirst().getDescriptionId()))).withStyle(ChatFormatting.BLUE)); // 速度
                } else if (amount < 0.0D) {
                    // 如果是降低某项属性值，则将提升值置为负数。
                    formattedAmount = formattedAmount * -1.0D;
                    // 添加红色文本
                    tooltip.add((Component.translatable(
                            "attribute.modifier.take." + modifier.getOperation().toValue(),
                            ItemStack.ATTRIBUTE_MODIFIER_FORMAT.format(formattedAmount),
                            Component.translatable(pair.getFirst().getDescriptionId()))).withStyle(ChatFormatting.RED));
                }
            }
        }
    }
}
