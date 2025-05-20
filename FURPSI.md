# FURPSI – Aside

A quiet breakdown of what defines the project.

---

## Functionality

- Ephemeral, peer-to-peer messaging over [Waku](https://waku.org)  
- One active session at a time  
- Clipboard-based invite sharing — leaves no trace  
- No accounts, no contacts, no media, no sync  
- No offline storage, retry logic, or message history  
- Works only when both devices are online  

**Constraints are features.**

---

## Usability

- Minimal UI: black screen, empty by default, only essential actions  
- One-tap create or paste session  
- No onboarding, no profile setup  
- “Exit” wipes local memory instantly  
- Message status: *Queued for sending* if peer is offline  

**Designed for clarity, urgency, and zero onboarding friction.**

---

## Reliability

- Depends on live peer presence via Waku  
- No persistence or retry  
- No session recovery  
- Works best in clean, direct network conditions
- Aside doesn’t verify identity. It relies on a shared response ritual, not a persistent login. A short session, confirmed through off-protocol timing and language, can be more secure than long-term accounts or biometrics.



**Built to fail gracefully — and by design, forget.**

---

## Performance

- Lightweight and fast  
- No background sync, push, or media  
- Minimal memory and network impact  
- Always cold start  

**Instant, because it loads almost nothing.**

---

## Supportability

- Fully open-source and forkable  
- No backend, no database, no infra  
- Android APK (sideload only)  
- Built in public on top of Waku  
- `README`, `principles.md`, and this file serve as orientation  

**Use it. Modify it. Fork it. Or let it disappear.**

---

## Intent

- If you opened Aside intentionally — it worked.  
- If nothing was left behind — it worked.  
- If you built something on top of it — it’s still working.
