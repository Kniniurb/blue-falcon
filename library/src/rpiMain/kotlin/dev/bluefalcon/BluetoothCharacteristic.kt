package dev.bluefalcon

import com.welie.blessed.BluetoothGattCharacteristic
import com.welie.blessed.BluetoothGattDescriptor
import kotlinx.coroutines.flow.MutableStateFlow
import java.util.UUID

actual class BluetoothCharacteristic(val characteristic: BluetoothGattCharacteristic) {
    actual val name: String?
        get() = characteristic.uuid.toString()
    actual val value: ByteArray?
        get() = mutableVal
    actual val descriptors: List<BluetoothCharacteristicDescriptor>
        get() = characteristic.descriptors.map { descriptor -> descriptor as BluetoothCharacteristicDescriptor } // characteristic.descriptors

    internal var mutableVal: ByteArray? = null
    internal actual val _descriptorsFlow = MutableStateFlow<List<BluetoothCharacteristicDescriptor>>(emptyList())
}

//actual typealias BluetoothCharacteristicDescriptor = BluetoothGattDescriptor
actual class BluetoothCharacteristicDescriptor: BluetoothGattDescriptor(UUID.randomUUID(), 0)
