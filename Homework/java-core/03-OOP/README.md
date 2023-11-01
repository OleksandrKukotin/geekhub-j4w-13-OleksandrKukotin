# OrkoStat - Analytic tool of negatively alive orks and their equipment

# 1. Business logic

### Measure damage

1. Count destroyed technics and orks
2. Count total damage by ZSU in dollars

### Model

1. **Ork**
    - Ork types
        1. `Ork`
        2. `Driver`
           - has driver license
        3. `Pilot`
           - has flew hours score
        4. ...TODO...
    - Cost a bit
        - default cost is Lada Vesta Sport price
        - cost can be customized for each ork
        - ork cost can increase during his life
        - scream on death
2. **Technique**
    - Technic types
        1. `Tank`
        2. `Aircraft`
        3. ...TODO...
    - Cost a bit. Cost is hardcoded for each type
    - Can shoot. Sound differently
    - Explodes with sound "Destroyed!" and every ork in equipage scream
    - Technic can be with equipage and can be also abandoned
        - Number of technic equipage is dynamic
        - Equipage loss increments orks as well
        - Equipage size has realistic limits
            - Tank contains from 0 to 6 orks
            - Orks are unique and not repeatable
3. **Collection**
    1. Simple implementation of collection
    2. Collection API should provide:
        1. Add new element
        2. Return elements
        3. Get collection size

# 2. Homework requirements

1. All existing tests should pass: be green and not modified
2. Add new technic type - `Ship`
    1. Has equipage
    2. Expensive
    3. Ruled by `Commander` (new ork type)
