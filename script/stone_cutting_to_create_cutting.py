import json
import os

root = 'C:/Users/13168/IdeaProjects/NeoKuayue-1192-dev/src/generated/resources/data/kuayue/recipes/stonecutting'

def resolve_path(path : str, func : function):
    files = os.listdir(path=path)
    for k in files:
        file_path = os.path.join(path, k)
        if (os.path.isfile(file_path)):
            func(file_path)
        elif os.path.isdir(file_path):
            resolve_path(file_path, func)


def resolve_file(file_path : str):
    with open(file_path, 'r') as f:
        file_json = json.loads(f.read())
        