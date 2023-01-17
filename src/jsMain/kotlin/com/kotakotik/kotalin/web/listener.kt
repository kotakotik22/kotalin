package com.kotakotik.kotalin.web

import org.w3c.dom.events.Event
import org.w3c.dom.events.EventTarget

// todo: test
/**
 * Adds a listener to this event target
 *
 * This method rearranges the parameters of [EventTarget.addEventListener] so that the lambda can be placed outside the parentheses
 */
fun EventTarget.addListener(type: String, options: dynamic = undefined, callback: (Event) -> Unit) =
    addEventListener(type, callback, options)

/**
 * Adds a listener to this event target
 *
 * On fire, the method casts the event to [Ev] and passes it to [callback]
 *
 * The cast is safe meaning an exception will be thrown if the event is not [Ev]
 */
inline fun <reified Ev : Event> EventTarget.addListenerExpecting(
    type: String,
    options: dynamic = undefined,
    crossinline callback: (Ev) -> Unit,
) =
    addListener(type, options) {
        callback(it as Ev)
    }