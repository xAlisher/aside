FURPS Breakdown

Functionality
Core Feature: Ephemeral, peer-to-peer chat with clipboard-based session invites.
Built on: Waku protocol — decentralized messaging.
Session model: One active session at a time. Messages sent only when both peers are online.
No history, no account, no metadata — every launch is a reset.
Constraints as features: No offline send/receive, no file transfer, no sync.
Intentionally minimal — does one thing and does it quietly.

Usability
UI: Monochrome, terminal-like, stripped to essentials.
Onboarding: No onboarding. Paste or create invite = entry.
Copy/Share model: Invite is shared manually via other messengers.
Exit: One tap to erase all—immediate, irreversible.
No buttons unless necessary: Radical simplicity is central to flow.
Designed for those who value clarity, silence, and frictionless intent.

Reliability
Depends on peer presence: Both devices must be online at the same time.
No retries, no background delivery — it's live or it’s not.
Waku stability is key: If the underlying protocol fails, message delivery fails.
No recovery model: By design, no way to restore sessions or messages.
Intentionally brittle to enforce ephemerality and presence.

Performance
Lightweight: Minimal UI, no media, no background sync = fast, small footprint.
Startup: Always cold boot to a blank state.
Resource use: Low battery/network impact unless active session is live.
Feels instant because there’s almost nothing to load.

Supportability
Open source: Fully transparent, forkable, inspectable.
Tech stack: Android-first, Waku-powered, clipboard-based flows.
No backend, no infra: No hosting, no user data to manage.
Docs: Optional principles.md, possibly README on GitHub, no user manual by design.
Debugging: Difficult by nature—logs are absence, not artifact.
Built for those who understand what it is and need it
Not built for users who need explanation.
 