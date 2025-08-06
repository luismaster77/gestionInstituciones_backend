
//Esto es para poder condicionar la plantilla del html. hay que validar como serán
//las validaciones

<#if promovidoGrado == "Sí">
    <p style="color: green;">¡Felicidades! Has sido promovido.</p>
<#else>
    <p style="color: red;">Lo sentimos, no has sido promovido.</p>
</#if>