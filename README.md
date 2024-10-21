# Unremovable Effects
Makes selected status effects unremovable with Milk Bucket


### [CurseForge](https://www.curseforge.com/minecraft/mc-mods/unremovableeffects)
### [Modrinth](https://modrinth.com/mod/unremovable-effects)

-------------------------------------------------------------

# Datapacks:
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
If you set `"replace"` to true, other files will be ignored. 

If there are multiple files with replace set to true, the one with **the highest** priority (`"replace_priority"`) will be used.

If multiple files has the same `"replace_priority"`, the one that was loaded first will be used (a warning in logs will appear in such situation)

