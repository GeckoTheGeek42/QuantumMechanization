package geek.lawsof.physics

import cpw.mods.fml.common.{ModMetadata, SidedProxy, Mod}
import cpw.mods.fml.common.Mod.EventHandler
import cpw.mods.fml.common.event._
import geek.lawsof.physics.lib.helper.Log
import geek.lawsof.physics.network.ModProxy
import geek.lawsof.physics.init._
import cpw.mods.fml.common.event.FMLInterModComms.IMCEvent
import geek.lawsof.physics.Reference._
import scala.collection.JavaConverters._

/**
 * Created by anshuman on 25-05-2014.
 */
@Mod(modid = MOD_ID, name = MOD_NAME, version = Reference.MOD_VERSION, modLanguage = MOD_LANGUAGE)
object LawsOfPhysicsMod {

  @SidedProxy(clientSide=CLIENT_PROXY_CLASS, serverSide=SERVER_PROXY_CLASS)
  var proxy: ModProxy = null

  @EventHandler
  def preInit(event: FMLPreInitializationEvent) = {
    Log.info("Theorizing the Laws Of Physics")

//    modMeta()

    preInitEvt = event

    ModConfigs.preInit()

    ModBlocks.preInit()
    ModItems.preInit()
    ModFluids.preInit()
    ModEquipment.preInit()
    ModMetals.preInit()

    Log.info("Laws Of Physics Theorizing Complete")
  }

  @EventHandler
  def init(event: FMLInitializationEvent) = {
    Log.info("Experimenting with the Laws Of Physics")

    initEvt = event

    ModBlocks.init()
    ModFluids.init()
    ModMetals.init()

    ModRecipies.init()

    CTabs.init()

    Log.info("Laws Of Physics Experimentation Complete")
  }

  @EventHandler
  def sendInterModComms(event: FMLInterModComms) = {
    Log.info("Telling Others about the Laws Of Physics")

    sendImcEvt = event

    Log.info("Hypothesis have been sent")
  }

  def IMC (event: IMCEvent) = {
    Log.info("Recieving Hypothesis about the Laws Of Physics")

    recImcEvt = event

    Log.info("Hypothesis Recieved")
  }

  @EventHandler
  def postInit(event: FMLPostInitializationEvent) = {
    Log.info("Proving the Laws Of Physics")

    postInitEvt = event

    Log.info("Laws Of Physics Have Been Proved ... Have Fun")
  }

  @EventHandler
  def serverStart(event: FMLServerStartingEvent) {
    Log.info(s"Implementing Laws Of Physics in World : ${event.getServer.getWorldName}")

    serverStartEvt = event

    Log.info(s"Laws Of Physics have been Implemented in World : ${event.getServer.getWorldName}")
  }

  def serverStop(event: FMLServerStoppingEvent) {
    Log.info("Shutting Down Laws Of Physics in World")

    serverStopEvt = event

    Log.info("Laws Of Physics have been Shut Down")
  }

  def modMeta () = {
    var meta: ModMetadata = preInitEvt.getModMetadata
    meta.autogenerated = false
    meta.modId = MOD_ID
    meta.name = MOD_NAME
    meta.version = MOD_VERSION
    meta.description = MOD_DESCRIPTION
    meta.credits = MOD_CREDITS
    meta.authorList = MOD_AUTHORS.asJava
    meta.url = MOD_URL
  }

  var preInitEvt: FMLPreInitializationEvent = null
  var initEvt: FMLInitializationEvent = null
  var sendImcEvt: FMLInterModComms = null
  var recImcEvt: IMCEvent = null
  var postInitEvt: FMLPostInitializationEvent = null
  var serverStartEvt: FMLServerStartingEvent = null
  var serverStopEvt: FMLServerStoppingEvent = null

}


