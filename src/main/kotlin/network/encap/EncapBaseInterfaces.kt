package network.encap

import enums.EtherTypes

/**
 * interface for all classes that can be encapsulated by L2 frame (e.g ARP, packet (aka IP), etc.)
 * */
interface L2{
    fun getEtherType(): EtherTypes
}
/**
 * interfaces for all classes that can be encapsulated by L3 packet (e.g TCP, UDP)
 * */
interface L3
/**
 * may not be needed, find out soon ^^
 * */
interface L4