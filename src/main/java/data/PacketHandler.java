/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package data;

import java.net.DatagramPacket;

public abstract class PacketHandler {

	public abstract void process(Packet packet);

}
