# ADR 0002: Package-by-Feature for Low-Level Designs (LLD)

## Status
Accepted

## Context
Layered packaging (e.g., placing all `models` across all problems in one package and all `services` in another) breaks encapsulation and makes individual design problems hard to read and evaluate.

## Decision
Package every LLD problem independently (`dev.anurag.handbook.lld.<system>`), collocating model, service, repository, and strategy within that problem's namespace.

## Consequences
- **Positive**: High cohesion; each problem is completely self-contained and modular.
- **Negative**: Minor duplication of generic value objects (e.g., `Direction`).
