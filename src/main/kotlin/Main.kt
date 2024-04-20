import network.packet.ipv4
import network.packet.packet

fun main(){
    try {
        val packet = packet {
            srcIp = ipv4{
                address = "127.0.0.1"
                mask = 24u
            }
            dstIp = ipv4 {
                address = "192.168.0.1"
                mask = 8u
            }
        }
        println(packet.srcIp.subnet())
    } catch(e: IllegalArgumentException){
        println(e.message)
    }
}