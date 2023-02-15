# StaffChat
Simple staff chat plugin for Zirkon Network that allows you to send a staff chat message easily.
## Building
Run `clean` and `package` with Maven then put the jar in your plugins folder and restart your server.
## Usage
Once in-game, use `/staffchat` to toggle staff chat and message away, use `/staffchat <message>` to send the included message, or put a\
single hashtag (`#`) in front of your message (e.x. `#Hello there`) to use staff chat without any commands.
## Permissions
- `sc.use`
  - Allows usage of `/staffchat` for toggling and sending messages
- `sc.view`
  - Makes messages sent in staff chat visible to the player
- `sc.manage`
  - Allows the player to reload the plugin
