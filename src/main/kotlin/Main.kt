import enums.Tos
import enums.plus
import exepctions.InvalidIPFormatException
import exepctions.InvalidMacAddressException
import network.frame.frame
import network.packet.packet
import utils.toIPv6
import utils.toIpV4

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
                srcIp = "192.168.10.1".toIpV4()
                dstIp = "127.0.0.1/24".toIpV4()
                tos = Tos.HIGH_PRECEDENCE + Tos.LOWDELAY
                ttl = 128u
            }
        }
        println(fr.print())
    } catch(e: IllegalArgumentException){
        println(e.message)
    } catch (e: InvalidIPFormatException){
        println(e.message)
    } catch (e: IllegalStateException) {
        println(e.message)
    } catch (e: InvalidMacAddressException) {
        println(e.message)
    }
}