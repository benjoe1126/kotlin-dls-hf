package delegates

import java.lang.IllegalStateException
import kotlin.properties.Delegates
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

//Delegate so IPV4 and IPV6 addressess cannot be overwritten
fun <T : Any> Delegates.once(): ReadWriteProperty<Any?, T> = object: ReadWriteProperty<Any?, T>{
    private var value: T? = null

    override fun getValue(thisRef: Any?, property: KProperty<*>): T {
        return value?: throw IllegalStateException("Property ${property.name} should initialized before get")
    }
    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        if(this.value != null){
            throw IllegalStateException("Property ${property.name} should only be set once")
        }
        this.value = value
    }
}