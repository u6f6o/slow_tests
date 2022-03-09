package org.acme

import io.quarkus.test.junit.NativeImageTest
import org.acme.ARepeatedTestIT

@NativeImageTest
class NativeGreetingResourceIT : ARepeatedTestIT()