import exepctions.InvalidIPFormatException
import exepctions.InvalidMacAddressException
import network.frame.frame
import network.packet.packetv4
import network.packet.packetv6
import utils.toIpV4

fun main(){
    try {
        val fr = frame{
            srcMac = "00:1F:00:00:00:00"
            dstMac = "00:00:00:00:00:00"
            data = packetv6{
                srcIp = "192.168.10.1".toIpV4()
                dstIp = "127.0.0.1/24".toIpV4()
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