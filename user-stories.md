# Aside – User Stories

---

## Shared Stories (Alice & Bob)

### Presence-Verified Communication
**As Alice or Bob, I want to know I’m talking to someone *now*, not just someone with the code,**  
**so I can trust they’re live and present for the sensitive topic.**

### Ephemeral Session Creation Without Shared Secret
**As Alice or Bob, I want to start a private session without sharing a secret in advance,**  
**so I’m protected even if someone intercepts the invite.**

### Generate Encryption Keys Only on Live Presence
**As Alice or Bob, I want encryption keys to be created only when both peers are present,**  
**so past messages can’t be decrypted if someone joins later.**

### Shared Symbol for Session Integrity
**As Alice or Bob, I want to see a shared emoji or symbol confirming the session identity,**  
**so I know the conversation wasn’t hijacked.**

### Auto-Wipe All Session Data on Exit
**As Alice or Bob, I want all session keys and chat data to vanish when I leave,**  
**so nothing remains after the conversation ends.**

### Copy-Paste Safe Invite Flow
**As Alice or Bob, I want to copy and share a session code without worrying about what’s inside it,**  
**so I don’t leak anything by inviting someone.**

---

## Initiator (Alice)

### Step Aside from Centralized Chat
**As Alice, I want to step aside and create a secure session outside centralized messengers,**  
**so I can share something sensitive without being tracked.**

### Auto-Copy Invite on Session Create
**As Alice, I want the session invite to copy automatically when I create a chat,**  
**so I can quickly share it without friction.**

### Queue Message if Peer is Offline
**As Alice, I want to write a message and have it queue if Bob is offline,**  
**so it sends only when he’s actually present.**

### Manual Exit Wipes Everything
**As Alice, I want to wipe the session instantly when I tap “Exit”,**  
**so there’s no trace left behind.**

### Notify Peer on Manual Exit
**As Alice, I want Bob to be notified when I leave the session,**  
**so he knows the conversation is over.**

### Auto-Exit on App Close
**As Alice, I want the session to end automatically if I close the app,**  
**so I don’t stay connected unintentionally.**

### Minimal Interface by Default
**As Alice, I want a distraction-free, minimal interface,**  
**so I don’t expose more than I mean to—even visually.**

---

## Responder (Bob)

### Paste Invite to Join Instantly
**As Bob, I want to paste an invite and enter a session without setup,**  
**so I can act quickly and stay anonymous.**

### Deliver Messages on Join
**As Bob, I want queued messages to deliver only when I connect,**  
**so I know nothing was sent before I was ready.**

### Real-Time Message Status
**As Bob, I want status indicators tied to live presence,**  
**so I trust what’s been seen, sent, or missed.**

### Notify Me When Alice Leaves
**As Bob, I want to be notified when Alice exits,**  
**so I don’t keep talking after she’s gone.**

### Auto-Exit on App Close
**As Bob, I want the session to end automatically if I close the app,**  
**so I don’t remain online by accident.**
