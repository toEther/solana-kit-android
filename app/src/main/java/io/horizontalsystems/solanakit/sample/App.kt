package io.definenulls.solanakit.sample

import android.app.Application
import io.horizontalsystems.hdwalletkit.Mnemonic
import io.definenulls.solanakit.Signer
import io.definenulls.solanakit.SolanaKit

class App : Application() {

    lateinit var solanaKit: SolanaKit
    lateinit var signer: Signer

    fun init() {
        assignKitClasses()
    }

    private fun assignKitClasses() {
        val words = Configuration.defaultsWords.split(" ")
        val seed = Mnemonic().toSeed(words, "")
        val address = Signer.address(seed)

        solanaKit = SolanaKit.getInstance(
            application = instance,
            addressString = address,
            rpcSource = Configuration.rpcSource,
            walletId = Configuration.walletId,
            solscanApiKey = Configuration.solscanApiKey,
            debug = true
        )

        signer = Signer.getInstance(seed)
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        this.init()
    }

    companion object {
        lateinit var instance: App
            private set
    }

}
