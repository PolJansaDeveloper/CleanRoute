# CleanRoute

CleanRoute is an offline-first Kotlin Multiplatform app for cleaning businesses to manage jobs, property checklists, before/after photos and client-ready reports.

## Stack
- Kotlin Multiplatform
- Compose Multiplatform
- SQLDelight
- Koin
- Coroutines + StateFlow
- kotlinx-datetime
- Material 3

## Implemented MVP Vertical Slice
- Shared Compose UI for Today/Jobs/Settings tabs.
- SQLDelight local database with jobs, checklist items, clients, and settings.
- Offline repository and Flow-based state updates.
- Seed-on-first-run baseline jobs.
- Job lifecycle actions prepared in repository.
- Common business-rule helper tests.

## Architecture
- Shared UI in `commonMain`.
- Feature-first shared domain/data/presentation layers.
- SQLDelight persistence, repository pattern, StateFlow view models.
- Offline-first local source of truth, prepared for future sync extension.

## Future Improvements
- Full client/property/template CRUD screens.
- Real camera/gallery integration with expect/actual pickers.
- PDF report generation + share sheet.
- Cloud sync/auth, recurring jobs, calendar sync, notifications.
