# Programació de Serveis i Processos — DAM2

Repositori de pràctiques del mòdul **M0490 · Programació de Serveis i Processos**
CFGS Desenvolupament d'Aplicacions Multiplataforma (DAM) — 2n curs — Curs 2026/2027

---

## Índex

- [Pràctiques](#pràctiques)
  - [PE01 — Classe per encriptar informació](#pe01--classe-per-encriptar-informació)
  - [PE02 — Control d'integritat](#pe02--control-dintegritat)
- [Estructura del repositori](#estructura-del-repositori)
- [Com compilar i executar](#com-compilar-i-executar)
- [Convencions](#convencions)

---

## Pràctiques

| Codi | Títol | RA | Tema | Estat |
| :--- | :--- | :---: | :--- | :--- |
| [PE01](PE01_Encriptació/README.md) | Classe per encriptar informació | RA5 | Encriptació i desencriptació de dades | 🟡 En curs |
| [PE02](PE02_Hash_Salt/) | Control d'integritat | RA5 | Funcions hash, integritat i salt | 🟡 En curs |

---

### PE01 — Classe per encriptar informació

> 📂 [`PE01_Encriptació/`](PE01_Encriptació/README.md) · 📄 Enunciat: [`PE01_Encriptació.pdf`](PE01_Encriptació/docs/PE01_Encriptació.pdf)

Disseny i implementació d'un **sistema de xifrat propi**, i després del mateix procés
amb **AES** (Advanced Encryption Standard) utilitzant l'API de criptografia de Java.

```
MISSATGE + CLAU          →  [ ENCRIPTACIÓ ]    →  MISSATGE XIFRAT
MISSATGE XIFRAT + CLAU   →  [ DESENCRIPTACIÓ ] →  MISSATGE ORIGINAL
```

Sistema propi escollit: **VX (Vigenère + XOR)**, xifrat simètric de flux per combinació
de dues tècniques vistes a classe. Detalls a [`PE01_Encriptació/README.md`](PE01_Encriptació/README.md).

**Lliurables**

- [ ] `ClasseCriptografica.java` — sistema de xifrat propi
- [ ] `ProgramaPrincipal.java` — programa principal del sistema propi
- [ ] `ClasseAES.java` — xifrat AES amb l'API de Java
- [ ] `ProgramaPrincipalAES.java` — programa principal d'AES
- [ ] Documentació en PDF amb les respostes i explicacions

**Punts clau de l'enunciat**

1. La classe criptogràfica **no conté** el programa principal: el principal és qui la fa servir.
2. La clau ha de participar **obligatòriament** en el procés de xifrat.
3. Ha de complir sempre: `Desencripta(Encripta(missatge, clau), clau) == missatge`.
4. Cal documentar les proves: missatge curt, amb espais, llarg (30+ caràcters),
   clau diferent, anada i tornada, i clau incorrecta.

---

### PE02 — Control d'integritat

> 📂 [`PE02_Hash_Salt/`](PE02_Hash_Salt/) · 📄 Enunciat: [`PE02_Hash_Salt.pdf`](PE02_Hash_Salt/docs/PE02_Hash_Salt.pdf)

Aplicació en Java per experimentar amb funcions **hash (SHA-256)**, verificar la
**integritat** dels missatges i fer un sistema de **registre i inici de sessió** amb
contrasenyes guardades com a hash amb **salt**.

```
MISSATGE              →  [ SHA-256 ]  →  HASH (hexadecimal)
CONTRASENYA + SALT    →  [ SHA-256 ]  →  HASH GUARDAT
```

**Parts**

- [ ] Part 1 — Càlcul de resums (SHA-256 en hexadecimal)
- [ ] Part 2 — Verificació de la integritat (`missatge.txt` i `missatge.hash`)
- [ ] Part 3 — Registre d'usuaris amb salt
- [ ] Part 4 — Inici de sessió i verificació de contrasenyes
- [ ] Part 5 — Proves de la pràctica i documentació

**Punts clau de l'enunciat**

1. Els hashes s'han de mostrar en format **hexadecimal**.
2. Cada registre ha de tenir una **salt aleatòria i diferent**.
3. **No** s'ha de guardar ni mostrar la contrasenya original un cop acabat el registre.
4. Només es permet l'accés quan el hash calculat coincideix amb el guardat.

---

## Estructura del repositori

```
Programació de serveis i processos/
├── .gitignore
├── README.md                       ← ets aquí
├── PE01_Encriptació/
│   ├── README.md                   ← guia de la pràctica
│   ├── docs/                       ← enunciat, disseny, proves i documentació
│   ├── src/                        ← codi font (.java)
│   └── bin/                        ← codi compilat (.class, ignorat pel repositori)
└── PE02_Hash_Salt/
    ├── docs/                       ← enunciat i exemple del professor (Hash01.java)
    ├── src/                        ← codi font (.java)
    └── bin/                        ← codi compilat (.class, ignorat pel repositori)
```

---

## Com compilar i executar

Cal tenir el **JDK 17 o superior** instal·lat (`java -version` per comprovar-ho).

Des de la carpeta de la pràctica:

```bash
javac -d bin src/*.java
java -cp bin ProgramaPrincipal
```

---

## Convencions

| Element | Convenció | Exemple |
| :--- | :--- | :--- |
| Carpetes de pràctica | `PEnn_Tema` | `PE01_Encriptació` |
| Classes | `PascalCase` | `ClasseCriptografica` |
| Mètodes i variables | `camelCase` | `encripta(missatge, clau)` |
| Codi font | dins de `src/` | `src/ClasseAES.java` |
| Codi compilat | dins de `bin/` (no es puja) | `bin/ClasseAES.class` |
| Documentació | dins de `docs/` | `docs/disseny.md` |
| Idioma | català al codi i a la documentació | |
