import exepctions.InvalidIPFormatException
import network.frame.frame
import network.packet.ipv6
import network.packet.packet
import utils.toIPv6

fun main(){
    try {
        /*
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
        */
        val fr = frame{
            srcMac = "00:1F:00:00:00:00"
            dstMac = "00:00:00:00:00:00"
            data = packet{
                srcIp = "2001:db8:acad::2/64".toIPv6()
                dstIp = "2001:db8:acad::1/64".toIPv6()
            }
        }.print()
        val ip6 = "2001:db8:acad::2/64".toIPv6()
        println(ip6.subnet())
    } catch(e: IllegalArgumentException){
        println(e.message)
    } catch (e: InvalidIPFormatException){
        println(e.message)
    }
}