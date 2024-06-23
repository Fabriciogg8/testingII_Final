# Examen Final TESTING II

## Autor 
**Fabricio González**

## Materia
Este proyecto es parte de la especialización de Backend dentro de la carrera Certified Tech Developer, bajo la instrucción del profesor **Sergio Pace**.

## Objetivo
El objetivo de este proyecto es crear un conjunto completo de Pruebas Automatizadas utilizando Java y Selenium, con Suites, Etiquetas e Informes, y validando también una API con RestAssured.

## Sistema Bajo Prueba
El sistema bajo prueba es Para Bank.

## Título del Caso de Prueba
Proceso de registro, apertura de una nueva cuenta, visión general de la cuenta, transferencia de fondos y actividad de la cuenta, para todas las etapas de las pruebas frontales.

## Pruebas Front-end
### Registro
1. Haga clic en `<Registro>`.
2. Rellene el formulario de registro con los datos requeridos.
3. Pulse de nuevo en `<Registro>`.
4. Compruebe que el texto "Su cuenta se ha creado correctamente. En la pantalla se puede ver "You are now logged in".

### Abrir una Nueva Cuenta
1. Haga clic en `<Abrir nueva cuenta>`.
2. En el apartado "¿Qué tipo de cuenta desea abrir?" seleccione la opción `<SAVINGS>`.
3. Haga clic en `<Abrir nueva cuenta>`.
4. Compruebe si el texto "Congratulations, your account is now open." está visible en la pantalla.

### Resumen de las Cuentas
1. Haga clic en `<Resumen de cuentas>`.
2. Compruebe si el texto "*El saldo incluye depósitos que pueden estar sujetos a retenciones" está visible en la pantalla.

### Transferir Fondos
1. Haga clic en `<Transferencia de fondos>`.
2. Compruebe que el texto "Transferir fondos" es visible en la pantalla.
3. En el campo `<Importe: $>` introduzca el importe a transferir.
4. En el campo `<De la cuenta #>` seleccione una cuenta.
5. En el campo `<a la cuenta #>` seleccione una cuenta distinta a la anterior.
6. Haga clic en `<Transferencia>`.
7. Compruebe que el texto "¡Transferencia completa!" es visible en la pantalla.

### Actividad de la Cuenta (cada mes)
1. Haga clic en `<Resumen de cuentas>`.
2. Compruebe que el texto "*El saldo incluye depósitos que pueden estar sujetos a retenciones" es visible en la pantalla.
3. Haga clic en una cuenta en la columna `<Cuenta>`.
4. Compruebe que el texto "Detalles de la cuenta" es visible en la pantalla.
5. En "Actividad de la cuenta" haga clic en `<Periodo de actividad:>` y seleccione "Todo".
6. En "Actividad de la cuenta" haga clic en `<Tipo:>` y seleccione "Todo".
7. Haga clic en `<Ir>`.

## Pruebas Back-end de la API Rest
### Validación del Código de Estado 200 para todas las etapas de las pruebas frontales
- Registro URL: [https://parabank.parasoft.com/parabank/register.htm](https://parabank.parasoft.com/parabank/register.htm)
- Abrir una nueva cuenta URL: [https://parabank.parasoft.com/parabank/services_proxy/bank/createAccount?customerId=12545&newAccountType=1&fromAccountId=xxxxx](https://parabank.parasoft.com/parabank/services_proxy/bank/createAccount?customerId=12545&newAccountType=1&fromAccountId=xxxxx)
- Resumen de las cuentas URL: [https://parabank.parasoft.com/parabank/overview.htm](https://parabank.parasoft.com/parabank/overview.htm)
- Descarga de fondos URL: [https://parabank.parasoft.com/parabank/services_proxy/bank/transfer?fromAccountId=13566&toAccountId=13677&amount=xxxxx](https://parabank.parasoft.com/parabank/services_proxy/bank/transfer?fromAccountId=13566&toAccountId=13677&amount=xxxxx)
- Actividad de la cuenta (cada mes) URL: [https://parabank.parasoft.com/parabank/services_proxy/bank/accounts/13566/transactions/month/All/type/All](https://parabank.parasoft.com/parabank/services_proxy/bank/accounts/13566/transactions/month/All/type/All)

## Organización del Proyecto
### Estructura del Proyecto
El proyecto está organizado en las siguientes partes:

1. **Pruebas Front-end con Selenium**: Incluye clases de página y pruebas automatizadas utilizando JUnit y Selenium WebDriver.
2. **Pruebas Back-end con RestAssured**: Valida los endpoints de la API asegurando que devuelven el código de estado correcto.

### Plan de Ejecución de Pruebas
Se han definido dos planes de ejecución de prueba:

- **Smoke Test Suite**: Contiene pruebas críticas que verifican que las funcionalidades principales del sistema funcionan correctamente.
- **Regression Test Suite**: Contiene todas las pruebas para asegurar que nuevas modificaciones no han introducido errores en el sistema existente.

### Etiquetas (Tags)
Utilice la anotación `@Tag` para agrupar los tests. Por ejemplo:

```java
@Test
@Tag("smoke")
public void testSearch1() {
    // Search test
}

### Informes
Se utilizan Extent Reports para generar informes detallados de las ejecuciones de pruebas.