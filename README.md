# Programació de Serveis i Processos — DAM2

Repositori de pràctiques del mòdul **M0490 · Programació de Serveis i Processos**
CFGS Desenvolupament d'Aplicacions Multiplataforma (DAM) — 2n curs — Curs 2026/2027

---

## Índex

- [Pràctiques](#pràctiques)
  - [PE01 — Classe per encriptar informació](#pe01--classe-per-encriptar-informació)
- [Estructura del repositori](#estructura-del-repositori)
- [Com compilar i executar](#com-compilar-i-executar)
- [Convencions](#convencions)

---

## Pràctiques

| Codi | Títol | RA | Tema | Estat |
| :--- | :--- | :---: | :--- | :--- |
| [PE01](PE01_Encriptació/README.md) | Classe per encriptar informació | RA5 | Encriptació i desencriptació de dades | 🟡 En curs |

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

## Estructura del repositori

```
Programació de serveis i processos/
├── .gitignore
├── README.md                       ← ets aquí
└── PE01_Encriptació/
    ├── README.md                   ← guia de la pràctica
    ├── docs/                       ← enunciat, disseny, proves i documentació
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
