# ms-yanki

Funcionalidad de monedero electronico a traves de mensajes

La arquitectura del proyecto fue realizada de manera Modular solo para la presentacion y funcioanmiento
Se crearon dos proyectos:
- ms-yanki-producer
    * Genera json de registro transacccion de pago mediante topico "transaction-yanki-topic"
    * Genera json de registro de billetera virtual (wallet) mediante topico "wallet-yanki-topic"
- ms-yanki-consumer
    * Efectua el consumo del topico de transaccion de pago
    * Efectua el consumo del topico de transaccion de registro de wallet

  


