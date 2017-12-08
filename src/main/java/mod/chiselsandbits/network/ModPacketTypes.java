package mod.chiselsandbits.network;

import java.util.HashMap;

import mod.chiselsandbits.network.packets.PacketAccurateSneakPlace;
import mod.chiselsandbits.network.packets.PacketBagGui;
import mod.chiselsandbits.network.packets.PacketBagGuiStack;
import mod.chiselsandbits.network.packets.PacketBlueprintSet;
import mod.chiselsandbits.network.packets.PacketChisel;
import mod.chiselsandbits.network.packets.PacketClearBagGui;
import mod.chiselsandbits.network.packets.PacketCompleteBlueprint;
import mod.chiselsandbits.network.packets.PacketOpenGui;
import mod.chiselsandbits.network.packets.PacketRotateVoxelBlob;
import mod.chiselsandbits.network.packets.PacketSetChiselMode;
import mod.chiselsandbits.network.packets.PacketSetColor;
import mod.chiselsandbits.network.packets.PacketShiftBluePrint;
import mod.chiselsandbits.network.packets.PacketSuppressInteraction;
import mod.chiselsandbits.network.packets.PacketUndo;
import mod.chiselsandbits.network.packets.WriteBlueprintPacket;

public enum ModPacketTypes
{
	CHISEL( PacketChisel.class ),
	OPEN_GUI( PacketOpenGui.class ),
	SET_CHISEL_MODE( PacketSetChiselMode.class ),
	ROTATE_VOXEL_BLOB( PacketRotateVoxelBlob.class ),
	BAG_GUI( PacketBagGui.class ),
	BAG_GUI_STACK( PacketBagGuiStack.class ),
	UNDO( PacketUndo.class ),
	CLEAR_BAG( PacketClearBagGui.class ),
	SUPRESS_INTERACTION( PacketSuppressInteraction.class ),
	SET_COLOR( PacketSetColor.class ),
	ACCURATE_PLACEMENT( PacketAccurateSneakPlace.class ),
	BLUEPRINT_SET( PacketBlueprintSet.class ),
	BLUEPRINT_COMPLETE( PacketCompleteBlueprint.class ),
	BLUEPRINT_SHIFT( PacketShiftBluePrint.class ),
	BLUEPRINT_WRITE( WriteBlueprintPacket.class );

	private final Class<? extends ModPacket> packetClass;

	ModPacketTypes(
			final Class<? extends ModPacket> clz )
	{
		packetClass = clz;
	}

	private static HashMap<Class<? extends ModPacket>, Integer> fromClassToId = new HashMap<Class<? extends ModPacket>, Integer>();
	private static HashMap<Integer, Class<? extends ModPacket>> fromIdToClass = new HashMap<Integer, Class<? extends ModPacket>>();

	public static void init()
	{
		for ( final ModPacketTypes p : ModPacketTypes.values() )
		{
			fromClassToId.put( p.packetClass, p.ordinal() );
			fromIdToClass.put( p.ordinal(), p.packetClass );
		}
	}

	public static int getID(
			final Class<? extends ModPacket> clz )
	{
		return fromClassToId.get( clz );
	}

	public static ModPacket constructByID(
			final int id ) throws InstantiationException, IllegalAccessException
	{
		return fromIdToClass.get( id ).newInstance();
	}

}
