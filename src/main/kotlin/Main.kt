import enums.DNSFlags
import enums.HTTPStatusCode
import enums.HTTPVersion
import exepctions.InvalidIPFormatException
import exepctions.InvalidMacAddressException
import network.frame.frame
import network.message.dns
import network.message.http
import network.packet.packetv4
import network.packet.packetv6
import network.segment.tcp
import network.segment.udp
import utils.toIpV4
import java.math.BigInteger
import java.time.LocalDateTime
import java.util.Date

fun main(){
    try {
        val fr = frame{
            srcMac = "00:1F:00:00:00:00"
            dstMac = "00:00:00:00:00:00"
            data = packetv4{
                srcIp = "192.168.10.1".toIpV4()
                dstIp = "127.0.0.1/24".toIpV4()
                ttl = 128u
                segment = tcp{
                    src = 69u
                    dst = 48u
                    ackNumber = 6822u
                    sequenceNumber = 6900u
                    windowSize = 1000u
                    data = dns{
                        opcode = 2323u
                        rcode = 233u
                        flag = DNSFlags.AA
                        ANCOUNT = 4.toBigInteger()
                        QDCOUNT = 25.toBigInteger()
                        NSCOUNT = 2131224.toBigInteger()
                        ARCOUNT = 1_000_000_000.toBigInteger()

                    }
                }
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