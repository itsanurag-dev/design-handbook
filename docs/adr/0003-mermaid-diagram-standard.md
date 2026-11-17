# ADR 0003: Text-Based Mermaid for Architectural Diagrams

## Status
Accepted

## Context
Binary assets (PNGs, PDFs) in git repositories cannot be diffed in PRs, degrade in clarity, and require external tools to modify.

## Decision
Mandate text-based Mermaid diagrams (`classDiagram`, `flowchart TD`, `sequenceDiagram`) directly inside Markdown documents for all LLD and HLD visual representations.

## Consequences
- **Positive**: Git-diffable, editable by any contributor, renders natively in GitHub web and IDE previewers.
- **Negative**: Complex diagrams with dozens of nodes can become visually crowded compared to specialized vector drawing suites.
