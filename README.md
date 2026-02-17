# BlueIce Server Tweaks
A simple mod that adds a few useful server-side features.
<hr>
This mod adds a few server-side features such as **commands**, **interactions** and **recipe addition and modifications** that may be useful in some situations.

There is a permission system powered by scoreboard for operators to control whether a player with no vanilla permission can use a command in **No requirement** list below.
# Contents
## Command List
### Requiring level 3
1. /setpermission string:command entity:player boolean:permission - Set the permission of /***command*** of ***player*** to ***permission***, use a **dot** (.) to represent all the commands;
2. /setlanguage string:language - Set server language to ***language***, supported inputs: **en_us**, **zh_cn**;
3. /logger ... - Write a line to log in *INFO*, *WARN* or *ERROR*;
4. /getinfos ... - Get some information of the server;
### Requiring level 2
5. /g itemstack:item - Get 1 stack of ***item***;
6. /gm string:gamemode - Change gamemode to ***gamemode***, supported inputs: **0**, **s**, **1**, **c**, **2**, **a**, **3**;
7. /debugamerule boolean:enabled - Change a set of gamerules that are useful when debugging;
### No requirement
8. /goto blockpos:location - Teleport to ***location*** if the chunk is loaded;
9. /compass blockpos:location string:name - Turn a Compass into a Lodestone Compass called ***name*** which points to ***location***;
10. /random ... - Generate random numbers that has nothing to do with the world seed;
11. /crafting - Open Crafting Table GUI;
12. /k - Kill yourself with confirmation;
13. /fly boolean:enabled - Toggle creative flight ability to ***enabled***;
14. /head string:playerName - Get a skull of player called ***playerName***;
15. /free ... - Provides a server-side free camera powered by *Spectator* mode;
16. /gamma boolean:enabled - Provides a server-side gamma override powered by *Night Vision* effect;
17. /hat - Put mainhand item to helmet slot if it is empty;
18. /painting string:id - Get a Painting with namespace ID ***id***;
19. /name string:name - Rename mainhand item to ***name***.
## Interaction List
1. **Shift and Right click** on a Block with **Hoe** in mainhand to collect the block if it is in **#blueice_server_tweaks:collectable** or break the block if it is in **#blueice_server_tweaks:destroyable**;
2. **Right click on Fletching Table** with **Arrows** in mainhand and **Potion or Glowstone Dust** in offhand to create **Tipped Arrows** much cheaper;
3. **Right click on Fletching Table** with **1 Mushroom Stew** in mainhand and **Potion** in offhand to create **Suspicious Stew** with full potion effect.
