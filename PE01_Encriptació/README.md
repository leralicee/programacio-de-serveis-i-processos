# Sistema escollit: xifrat VX (Vigenère + XOR)


PARAULA: HOLA -> H=72, O=79, L=76, A=65
CLAU: 123 -> '1'=49, '2'=50, '3'=51

A CADA LLETRA SUMEM:
- un caracter de la clau (49, 50, 51... en bucle)
- la posicio de la lletra (0, 1, 2, 3...)
- la suma de tota la clau (49+50+51 = 150)

SI PASA DE 255, RESTEM 256

AL RESULTAT ES FA XOR AMB EL SEGUENT CARACTER DE LA CLAU (50)

EL RESULTAT ES PASA A BASE64



PER DESFER: DESFER BASE64, FER XOR AMB SEGUENT CARACTER DE LA CLAU, I RESTAR EN COMPTES DE SUMAR


EXEMPLE: 
H = 72
72 + 49 (el '1' de la clau)
   + 0 (es la primera lletra)
   + 150 (suma de la clau)
   = 271 -> ens pasem de 255 -> 271-256 = 15
15 XOR 50 (el '2' de la clau) = 61
igual amb la O, la L y la A → 61, 43, 38, 57 -> Base64 -> PSsmOQ==


sumem posicio pq HOLAHOLA no doni resultat repetit 2 cops
sumem la suma d la clau pq si t'equivoques en un caracter es trenqui tot