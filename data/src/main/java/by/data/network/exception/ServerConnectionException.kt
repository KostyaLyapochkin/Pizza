package by.data.network.exception

import java.io.IOException

class ServerConnectionException(override val message: String?) : IOException(message)