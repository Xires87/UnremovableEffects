# Unremovable Effects
Adds a possibility of selecting which status effects should not be removed with Milk Bucket and adds possibility of
making an item a cure for selected status effects


### [CurseForge](https://www.curseforge.com/minecraft/mc-mods/unremovableeffects)
### [Modrinth](https://modrinth.com/mod/unremovable-effects)

-------------------------------------------------------------

# Datapacks:

### Making effects unremovable with milk bucket

To make status effect unremovable, in `data/unremovableeffects/unremovable_status_effects/` create json file containing:
```json
{
  "replace": false,
  "replace_priority": 0,
  "status_effects": [

  ]
}
```
In `"status_effects"` add status effects which you want to make unremovable, for example:
```json
{
  "replace": false,
  "replace_priority": 0,
  "status_effects": [
    "minecraft:speed",
    "minecraft:poison",
    "some_mod_id:some_status_effect",
    "some_mod_id:another_status_effect"
  ]
}
```

### Making items remove selected status effects

To make item remove selected status effects, in `data/unremovableeffects/items_remove_status_effects/` create json file containing:
```json
{
  "replace": false,
  "replace_priority": 0,
  "item": "",
  "status_effects": [

  ]
}
```

In `"item"` add item which you want to be a cure for status effects specified in `"status_effects"`, for example:
```json
{
  "replace": false,
  "replace_priority": 0,
  "item": "minecraft:cooked_porkchop",
  "status_effects": [
    "minecraft:blindness",
    "minecraft:darkness",
    "some_mod_id:some_status_effect",
    "some_mod_id:another_status_effect"
  ]
}
```

**Note:** Selected item needs to be consumed to remove effects - not all items will work

### About 'replace'

If you set `"replace"` to true, other files will be ignored. 

If there are multiple files with replace set to true, the one with **the highest** priority (`"replace_priority"`) will be used.

If multiple files has the same `"replace_priority"`, the one that was loaded first will be used (a warning in logs will appear in such situation)

### Additional info

Honey bottle's ability to remove poison has been removed and re-added via json file 
(`data/unremovableeffects/items_remove_status_effects/honey_removes_poison.json`)