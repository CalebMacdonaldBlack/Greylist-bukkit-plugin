# Greylist
A Minecraft bukkit plugin


Players with the `Greylist.greylisted` permission will have the following effects:
* Cannot be damaged by mobs or players
* Cannot be chased by mobs
* Cannot interact with anything (destroy/place blocks, open chests/doors etc)
* No hunger depletion
* Give infinite regeneration when joining the server

Other  notes:
* Players without the `Greylist.greylisted` permission will have their infinite regeneration removed when joining the server
* A third party plugin should remove the infinite regen effect when a logged in player is promoted
