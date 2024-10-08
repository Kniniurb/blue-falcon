package dev.bluefalcon

import kotlinx.coroutines.flow.MutableStateFlow
import platform.CoreBluetooth.CBPeripheral
import platform.CoreBluetooth.CBService

actual class BluetoothPeripheral(val bluetoothDevice: CBPeripheral, val rssiValue: Float?) {
    actual val name: String? = bluetoothDevice.name
    actual var rssi: Float? = rssiValue
    actual var mtuSize: Int? = null
    actual val services: List<BluetoothService>
        get() = bluetoothDevice.services?.map {
            BluetoothService(it as CBService)
        } ?: emptyList()
    actual val uuid: String = bluetoothDevice.identifier.UUIDString

    internal actual val _servicesFlow = MutableStateFlow<List<BluetoothService>>(emptyList())

    override fun toString(): String = uuid

    override fun hashCode(): Int = uuid.hashCode()
    override fun equals(other: Any?): Boolean {
        if (other == null || other !is BluetoothPeripheral) return false
        return other.uuid == uuid
    }
}