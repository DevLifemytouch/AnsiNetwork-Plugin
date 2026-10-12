# AnsiNetwork TODO's

_Alle wichtigen TODO's_

> ### <span style="color: #60A5FA;">Wichtige Informationen:</span>
> 
> ---
> 
> <span style="color: #C084FC;">**Prioritäten:**</span>
> * P0 - <span style="color: #F87171">kritisch:</span> Höchste Priorität, so schnell wie möglich bearbeiten!
> * P1 - <span style="color: #FB923C">hoch:</span> Bearbeiten, sobald alle P0's weg sind
> * P2 - <span style="color: #FBBF24">mittel:</span> Aktuell nicht notwendig für das Funktionieren des Plugins
> * P3 - <span style="color: #4ADE80">niedrig:</span> Extra Features, die nicht sofort gebraucht werden
> 
> <span style="color: #C084FC;">**Begriffe:**</span>
> * BUG: Ein Bug ist ein kleiner Fehler innerhalb des Programms
> * FEAT: Ein ``FEAT`` ist ein Feature das umgesetzt werden muss
> * ENG: ``ENG`` steht für Engineering. Es zeigt Sachen im Code die verbessert werden müssen.
> * IDEA: ``IDEA`` steht für Ideen, die das Programm verbessern könnten.

> ### <span style="color: #60A5FA;">Regeln für TODO's:</span>
> 
> ---
> 
> 1. Jedes TODO muss verständlich und eindeutig formuliert sein.
> 2. Beschreibe möglichst konkret, was eingebaut oder verbessert werden soll
> 3. Schreibe zu jedem TODO deinen Namen dazu!
>    * z.B. ``// ENG: ... --- Waitinglobby``
> 4. Weise jedem TODO eine Kategorie und Priorität zu! 
>    * `// BUG: Try-/Catch-Block wirft falschen Fehler`
> 5. Teile große Aufgaben in kleinere, **unabhängige** Schritte auf.
> 6. Vermeide redundante TODO's. Überprüfe, ob das TODO in gleicher oder ähnlicher Form schon existiert. 
> Ergänzen, wenn es sein muss.
> 7. Wenn eine Aufgabe unklar ist, kläre die offenen Fragen erst mit der Person, die das TODO verfasst hat.

> ### <span style="color: #60A5FA;">Allgemeiner Workflow:</span>
> 1. **Auswahl:** Wähle ein offenes TODO aus.
> 2. **Verstehen:** Lies den vorhandenen Code durch und prüfe relevante Dependencies.
> 3. **Implementierung:** Setze die Änderung um.
> 4. **Test:** Teste die Änderung.
> 5. **Review:** Erstelle eine Pull-Request.
> 6. **Complete:** Markiere das TODO erst als erledigt, wenn alle Features fehlerfrei sind.

> ### <span style="color: #60A5FA;">DoD ( Definition of Done ):</span>
> Ein TODO gilt dann als abgeschlossen, wenn...
> 
> - [ ] ...das Feature vollständig implementiert ist.
> - [ ] ...relevante Tests fehlerfrei durchgeführt werden können.
> - [ ] ...bestehende Features nicht beeinträchtigt werden.
> - [ ] ...notwendige Dokumentationen aktualisiert wurden.
> - [ ] ...das Code Review vom **Lead Developer** abgeschlossen ist.
> 
> Nicht jeder Punkt ist bei jedem TODO relevant. Abweichungen sollen begründet werden.

> ### <span style="color: #60A5FA;">Beispiel für ein gutes TODO:</span>
> - [ ] **[P1] FEAT:** Füge Permission-Check zu /fly hinzu
>   - **Context:** Spieler ohne Permission ``ansi.commands.fly`` können den Command ausführen.
>   - **Erwartetes Ergebnis:** Nur Spieler mit der Permission können den Command ausführen
>   - **Kriterien:**
>     - [ ] Permission wird vor der Ausführung geprüft.
>     - [ ] Spieler ohne Permission erhalten ``Messages.getNO_PERMS()``
>     - [ ] Rest bleibt unverändert.

---

## ansi-common/

> ### P0 - <span style="color: #F87171">kritisch:</span> 
> - [ ] **BUG:**
> ### P1 - <span style="color: #FB923C">hoch:</span> 
> - [ ] **FEAT:**
> ### P2 - <span style="color: #FBBF24">mittel:</span> 
> - [ ] **ENG:**
> ### P3 - <span style="color: #4ADE80">niedrig:</span> 
> - [ ] **IDEA:**

---

## ansi-proxy/

> ### P0 - <span style="color: #F87171">kritisch:</span>
> - [ ] **BUG:**
> ### P1 - <span style="color: #FB923C">hoch:</span>
> - [ ] **FEAT:**
> ### P2 - <span style="color: #FBBF24">mittel:</span>
> - [ ] **ENG:**
> ### P3 - <span style="color: #4ADE80">niedrig:</span>
> - [ ] **IDEA:**

---

## ansi-server/

> ### P0 - <span style="color: #F87171">kritisch:</span>
> - [ ] **BUG:**
> ### P1 - <span style="color: #FB923C">hoch:</span>
> - [ ] **FEAT:**
> ### P2 - <span style="color: #FBBF24">mittel:</span>
> - [ ] **ENG:**
> ### P3 - <span style="color: #4ADE80">niedrig:</span>
> - [ ] **IDEA:**

---