# Messages

## What we believe

Fast, clear dialog builds trust. UI should never block flow or hide state.

---

## Scope

Spec for **MessageList** UI, message & peer state machines, and **DebugPeerSimulator**. All names below align with the real Compose components & enums so this doc remains single‑source‑of‑truth.

---

### 1 · Component ↔ Code map

| Layer  | Compose file / class       | Core enums (from files)                                                    |
| ------ | -------------------------- | -------------------------------------------------------------------------- |
| Top    | `SessionTopBar` *(static)* | –                                                                          |
| Status | `ConnectionStatus.kt`      | `PeerState { Offline, Connecting, Connected, Exited }`                     |
| Middle | `` *(new)*                 | Uses `Message.kt` bubbles                                                  |
| Bubble | `Message.kt`               | `MessageType { In, Out }` + `MessageStatus`                                |
| Bottom | `InputField.kt`            | – calls `SendQueueButton`                                                  |
| Button | `SendQueueButton.kt`       | `ButtonType { Send, Queue }`, `ButtonState { Default, Pressed, Disabled }` |
| Action | `ExitButton.kt`            | –                                                                          |

`MessageList` stretches between `SessionTopBar` and `InputField` and stays scrollable.

---

### 2 · Auto‑scroll rules

- **Default** – snap to newest whenever `firstVisibleItemIndex == 0`.
- **User scrolled up** – freeze; later surface a *“New messages”* banner (out‑of‑scope).
- **User sends** – always `animateScrollToItem(0)`.
- **Peer Exited** – drop `InputField`; list fills to bottom.

---

### 3 · State machines

#### Peer connection

```kotlin
enum class PeerState { Offline, Connecting, Connected, Exited }
```

*Meaning*`Connecting` is the in‑between: the app is actively trying to (re)establish the Waku link but has not yet confirmed the peer’s real state. It covers **both** the initial handshake **and** transient network drops where the outcome is unknown.

| From → To              | Trigger                                              |
| ---------------------- | ---------------------------------------------------- |
| Offline → Connecting   | Invite accepted **or** first send attempt after loss |
| Connecting → Connected | Handshake / heartbeat succeeds                       |
| Connected → Connecting | Missed *n* heartbeats (temporary link loss)          |
| Connecting → Offline   | `gracePeriod` (e.g. 30 s) with no response           |
| *any* → Exited         | Peer explicitly closes session                       |

#### Message delivery

```kotlin
enum class MessageStatus { Queued, Sent, Delivered, Failed }
```

| From → To        | Trigger                                   |
| ---------------- | ----------------------------------------- |
| Queued → Sent    | App retries when `PeerState == Connected` |
| Sent → Delivered | Delivery receipt from peer                |
| \* → Failed      | Transport error / timeout                 |

*(Sending spinner/progress indicator deferred for now; bubble enters **`Queued`** and flips to **`Sent`** once the coroutine completes.)*

---

### 4 · Send logic

- **Peer Offline or Connecting**
  - Button label = **Queue**.
  - Message stored in RAM with `MessageStatus.Queued`.
- **Peer Connected**
  - Attempt P2P send.
  - Success → `Sent`, then `Delivered` on receipt.
  - Failure → `Failed` (manual tap‑to‑retry later).
- **Peer Exited**
  - Hide keyboard & input; block sends.
- **Exit action**
  - Purge in‑memory list → navigate **Home** → toast *Nothing here* (see `ExitButton`).

---

### 5 · DebugPeerSimulator (dev‑only)

You can simulate peer behavior in two ways:

1. **Manual cycling in debug mode**
   Wrap `ConnectionStatus` in:
   ```kotlin
   if (BuildConfig.DEBUG) {
     ConnectionStatus(
       state = peerState,
       onClick = {
         peerState = when (peerState) {
           PeerState.Offline -> PeerState.Connecting
           PeerState.Connecting -> PeerState.Connected
           PeerState.Connected -> PeerState.Exited
           PeerState.Exited -> PeerState.Offline
         }
       }
     )
   }
   ```
   This lets you tap the label to simulate full peer state cycle.

2. **Scripted DebugPeerSimulator** (future)
  - After invite: wait 40s → `Connected`
  - Auto-drain: `Queued → Sent → Delivered`
  - Inject mock inbound messages

---

### 6 · Implementation order

1. **MessageList** skeleton + scroll logic.
2. Local queue renderer using `MessageStatus`.
3. **DebugPeerSimulator** to verify flow.
4. Plug real Waku transport.
5. Edge polish (new‑banner, retry, failure toast).

---

### 7 · Guardrails

- Zero disk persistence—for now RAM only.
- No automatic retries/back‑off yet.
- Keep all heavy work off the main thread.

---

*Last updated: 2025‑06‑10*
