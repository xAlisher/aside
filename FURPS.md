# FURPS – Aside

A quiet breakdown of what defines the project.

---

## 🔧 Functionality

- Ephemeral, peer-to-peer messaging over [Waku](https://waku.org)
- One active session at a time
- Clipboard-based invite sharing
- No accounts, no contacts, no media, no sync
- No offline storage, retry logic, or message history
- Works only when both devices are online

→ Constraints are intentional and converted into features.

---

## 🎛 Usability

- Minimal UI: black screen, monospaced feel, only essential actions
- One-tap create or paste session
- No onboarding, no profile setup
- “Exit” button wipes local memory instantly
- Message status: *Queued for sending* if peer is offline

→ Designed for clarity, urgency, and zero onboarding friction.

---

## 🛡 Reliability

- Depends on live peer presence via Waku
- No persistence or delivery retry
- No user state or session recovery
- Works best in clear network conditions
- If either peer exits, conversation ends

→ Built to fail gracefully—and by design, forget.

---

## ⚡ Performance

- Lightweight and fast
- No background sync, push notifications, or media assets
- Minimal memory and network usage
- Cold boot by design

→ Feels instant because it loads... almost nothing.

---

## 🔧 Supportability

- Fully open-source and forkable
- No backend, no database, no infra
- Android APK (sideload only)
- Built in public on top of Waku
- `README`, `principles.md`, and this file serve as orientation

→ Use it, modify it, fork it, or let it disappear.

---

If it disappears, it worked.
