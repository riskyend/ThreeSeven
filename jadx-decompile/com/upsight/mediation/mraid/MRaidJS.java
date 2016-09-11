package com.upsight.mediation.mraid;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MRaidJS {
    public static final String value = "<script language=\"javascript\" type=\"text/javascript\">\n(function() {\n\n\tconsole.log(\"MRAID object loading...\");\n\n\t/***************************************************************************\n\t * console logging helper\n\t **************************************************************************/\n\n\tvar LogLevelEnum = {\n\t\t\"DEBUG\"   : 0,\n\t\t\"INFO\"    : 1,\n\t\t\"WARNING\" : 2,\n\t\t\"ERROR\"   : 3,\n\t\t\"NONE\"    : 4\n\t};\n\n\tvar logLevel = LogLevelEnum.NONE;\n\tvar log = {};\n\n\tlog.d = function(msg) {\n\t\tif (logLevel <= LogLevelEnum.DEBUG) {\n\t\t\tconsole.log(\"(D-mraid.js) \" + msg);\n\t\t}\n\t};\n\n\tlog.i = function(msg) {\n\t\tif (logLevel <= LogLevelEnum.INFO) {\n\t\t\tconsole.log(\"(I-mraid.js) \" + msg);\n\t\t}\n\t};\n\n\tlog.w = function(msg) {\n\t\tif (logLevel <= LogLevelEnum.WARNING) {\n\t\t\tconsole.log(\"(W-mraid.js) \" + msg);\n\t\t}\n\t};\n\n\tlog.e = function(msg) {\n\t\tif (logLevel <= LogLevelEnum.ERROR) {\n\t\t\tconsole.log(\"(E-mraid.js) \" + msg);\n\t\t}\n\t};\n\n\t/***************************************************************************\n\t * MRAID declaration\n\t **************************************************************************/\n\n\tvar mraid = window.mraid = {};\n\n\t/***************************************************************************\n\t * constants\n\t **************************************************************************/\n\n\tvar VERSION = \"2.0\";\n\n\tvar STATES = mraid.STATES = {\n\t\t\"LOADING\" : \"loading\",\n\t\t\"DEFAULT\" : \"default\",\n\t\t\"EXPANDED\" : \"expanded\",\n\t\t\"RESIZED\" : \"resized\",\n\t\t\"HIDDEN\" : \"hidden\"\n\t};\n\n\tvar PLACEMENT_TYPES = mraid.PLACEMENT_TYPES = {\n\t\t\"INLINE\" : \"inline\",\n\t\t\"INTERSTITIAL\" : \"interstitial\"\n\t};\n\n\tvar RESIZE_PROPERTIES_CUSTOM_CLOSE_POSITION = mraid.RESIZE_PROPERTIES_CUSTOM_CLOSE_POSITION = {\n\t\t\"TOP_LEFT\" : \"top-left\",\n\t\t\"TOP_CENTER\" : \"top-center\",\n\t\t\"TOP_RIGHT\" : \"top-right\",\n\t\t\"CENTER\" : \"center\",\n\t\t\"BOTTOM_LEFT\" : \"bottom-left\",\n\t\t\"BOTTOM_CENTER\" : \"bottom-center\",\n\t\t\"BOTTOM_RIGHT\" : \"bottom-right\"\n\t};\n\n\tvar ORIENTATION_PROPERTIES_FORCE_ORIENTATION = mraid.ORIENTATION_PROPERTIES_FORCE_ORIENTATION = {\n\t\t\"PORTRAIT\" : \"portrait\",\n\t\t\"LANDSCAPE\" : \"landscape\",\n\t\t\"NONE\" : \"none\"\n\t};\n\n\tvar EVENTS = mraid.EVENTS = {\n\t\t\"ERROR\" : \"error\",\n\t\t\"READY\" : \"ready\",\n\t\t\"SIZECHANGE\" : \"sizeChange\",\n\t\t\"STATECHANGE\" : \"stateChange\",\n\t\t\"VIEWABLECHANGE\" : \"viewableChange\"\n\t};\n\n\tvar SUPPORTED_FEATURES = mraid.SUPPORTED_FEATURES = {\n\t\t\"SMS\" : \"sms\",\n\t\t\"TEL\" : \"tel\",\n\t\t\"CALENDAR\" : \"calendar\",\n\t\t\"STOREPICTURE\" : \"storePicture\",\n\t\t\"INLINEVIDEO\" : \"inlineVideo\"\n\t};\n\n\t/***************************************************************************\n\t * state\n\t **************************************************************************/\n\n\tvar state = STATES.LOADING;\n\tvar placementType = PLACEMENT_TYPES.INLINE;\n\tvar supportedFeatures = {};\n\tvar isViewable = false;\n\tvar isExpandPropertiesSet = false;\n\tvar isResizeReady = false;\n\n\tvar expandProperties = {\n\t\t\"width\" : 0,\n\t\t\"height\" : 0,\n\t\t\"useCustomClose\" : false,\n\t\t\"isModal\" : true\n\t};\n\n\tvar orientationProperties = {\n\t\t\"allowOrientationChange\" : true,\n\t\t\"forceOrientation\" : ORIENTATION_PROPERTIES_FORCE_ORIENTATION.NONE\n\t};\n\n\tvar resizeProperties = {\n\t\t\"width\" : 0,\n\t\t\"height\" : 0,\n\t\t\"customClosePosition\" : RESIZE_PROPERTIES_CUSTOM_CLOSE_POSITION.TOP_RIGHT,\n\t\t\"offsetX\" : 0,\n\t\t\"offsetY\" : 0,\n\t\t\"allowOffscreen\" : true\n\t};\n\n\tvar currentPosition = {\n\t\t\"x\" : 0,\n\t\t\"y\" : 0,\n\t\t\"width\" : 0,\n\t\t\"height\" : 0\n\t};\n\n\tvar defaultPosition = {\n\t\t\"x\" : 0,\n\t\t\"y\" : 0,\n\t\t\"width\" : 0,\n\t\t\"height\" : 0\n\t};\n\n\tvar maxSize = {\n\t\t\"width\" : 0,\n\t\t\"height\" : 0\n\t};\n\n\tvar screenSize = {\n\t\t\"width\" : 0,\n\t\t\"height\" : 0\n\t};\n\n\tvar currentOrientation = 0;\n\n\tvar listeners = {};\n\n\t/***************************************************************************\n\t * \"official\" API: methods called by creative\n\t **************************************************************************/\n\n\tmraid.addEventListener = function(event, listener) {\n\t\tlog.i(\"mraid.addEventListener \" + event + \": \" + String(listener));\n\t\tif (!event || !listener) {\n\t\t\tmraid.fireErrorEvent(\"Both event and listener are required.\", \"addEventListener\");\n\t\t\treturn;\n\t\t}\n\t\tif (!contains(event, EVENTS)) {\n\t\t\tmraid.fireErrorEvent(\"Unknown MRAID event: \" + event, \"addEventListener\");\n\t\t\treturn;\n\t\t}\n\t\tvar listenersForEvent = listeners[event] = listeners[event] || [];\n\t\t// check to make sure that the listener isn't already registered\n\t\tfor (var i = 0; i < listenersForEvent.length; i++) {\n\t\t\tvar str1 = String(listener);\n\t\t\tvar str2 = String(listenersForEvent[i]);\n\t\t\tif (listener === listenersForEvent[i] || str1 === str2) {\n\t\t\t\tlog.i(\"listener \" + str1 + \" is already registered for event \" + event);\n\t\t\t\treturn;\n\t\t\t}\n\t\t}\n\t\tlistenersForEvent.push(listener);\n\t};\n\n\tmraid.createCalendarEvent = function(parameters) {\n\t\tlog.i(\"mraid.createCalendarEvent with \" + parameters);\n\t\tif (supportedFeatures[mraid.SUPPORTED_FEATURES.CALENDAR]) {\n\t\t\tcallNative(\"createCalendarEvent?eventJSON=\"\t+ JSON.stringify(parameters));\n\t\t} else {\n\t\t\tlog.e(\"createCalendarEvent is not supported\");\n\t\t}\n\t};\n\n\tmraid.close = function() {\n\t\tlog.i(\"mraid.close\");\n\t\tif (state === STATES.LOADING\n\t\t\t\t|| (state === STATES.DEFAULT && placementType === PLACEMENT_TYPES.INLINE)\n\t\t\t\t|| state === STATES.HIDDEN) {\n\t\t\t// do nothing\n\t\t\treturn;\n\t\t}\n\t\tcallNative(\"close\");\n\t};\n\n\tmraid.expand = function(url) {\n\t\tif (url === undefined) {\n\t\t\tlog.i(\"mraid.expand (1-part)\");\n\t\t} else {\n\t\t\tlog.i(\"mraid.expand \" + url);\n\t\t}\n\t\t// The only time it is valid to call expand is when the ad is\n\t\t// a banner currently in either default or resized state.\n\t\tif (placementType !== PLACEMENT_TYPES.INLINE\n\t\t\t\t|| (state !== STATES.DEFAULT && state !== STATES.RESIZED)) {\n\t\t\treturn;\n\t\t}\n\t\tif (url === undefined) {\n\t\t\tcallNative(\"expand\");\n\t\t} else {\n\t\t\tcallNative(\"expand?url=\" + encodeURIComponent(url));\n\t\t}\n\t};\n\n\tmraid.getCurrentPosition = function() {\n\t\tlog.i(\"mraid.getCurrentPosition\");\n\t\treturn currentPosition;\n\t};\n\n\tmraid.getDefaultPosition = function() {\n\t\tlog.i(\"mraid.getDefaultPosition\");\n\t\treturn defaultPosition;\n\t};\n\n\tmraid.getExpandProperties = function() {\n\t\tlog.i(\"mraid.getExpandProperties\");\n\t\treturn expandProperties;\n\t};\n\n\tmraid.getMaxSize = function() {\n\t\tlog.i(\"mraid.getMaxSize\");\n\t\treturn maxSize;\n\t};\n\n\tmraid.getOrientationProperties = function() {\n\t\tlog.i(\"mraid.getOrientationProperties\");\n\t\treturn orientationProperties;\n\t};\n\n\tmraid.getPlacementType = function() {\n\t\tlog.i(\"mraid.getPlacementType\");\n\t\treturn placementType;\n\t};\n\n\tmraid.getResizeProperties = function() {\n\t\tlog.i(\"mraid.getResizeProperties\");\n\t\treturn resizeProperties;\n\t};\n\n\tmraid.getScreenSize = function() {\n\t\tlog.i(\"mraid.getScreenSize\");\n\t\treturn screenSize;\n\t};\n\n\tmraid.getState = function() {\n\t\tlog.i(\"mraid.getState\");\n\t\treturn state;\n\t};\n\n\tmraid.getVersion = function() {\n\t\tlog.i(\"mraid.getVersion\");\n\t\treturn VERSION;\n\t};\n\n\tmraid.isViewable = function() {\n\t\tlog.i(\"mraid.isViewable\");\n\t\treturn isViewable;\n\t};\n\n\tmraid.open = function(url) {\n\t\tlog.i(\"mraid.open \" + url);\n\t\tcallNative(\"open?url=\" + encodeURIComponent(url));\n\t};\n\n\tmraid.playVideo = function(url) {\n\t\tlog.i(\"mraid.playVideo \" + url);\n\t\tcallNative(\"playVideo?url=\" + encodeURIComponent(url));\n\t};\n\n\tmraid.removeEventListener = function(event, listener) {\n\t\tlog.i(\"mraid.removeEventListener \" + event + \" : \" + String(listener));\n\t\tif (!event) {\n\t\t\tmraid.fireErrorEvent(\"Event is required.\", \"removeEventListener\");\n\t\t\treturn;\n\t\t}\n\t\tif (!contains(event, EVENTS)) {\n\t\t\tmraid.fireErrorEvent(\"Unknown MRAID event: \" + event, \"removeEventListener\");\n\t\t\treturn;\n\t\t}\n\t\tif (listeners.hasOwnProperty(event)) {\n\t\t\tif (listener) {\n\t\t\t\tvar listenersForEvent = listeners[event];\n\t\t\t\t// try to find the given listener\n\t\t\t\tvar len = listenersForEvent.length;\n\t\t\t\tfor (var i = 0; i < len; i++) {\n\t\t\t\t\tvar registeredListener = listenersForEvent[i];\n\t\t\t\t\tvar str1 = String(listener);\n\t\t\t\t\tvar str2 = String(registeredListener);\n\t\t\t\t\tif (listener === registeredListener || str1 === str2) {\n\t\t\t\t\t\tlistenersForEvent.splice(i, 1);\n\t\t\t\t\t\tbreak;\n\t\t\t\t\t}\n\t\t\t\t}\n\t\t\t\tif (i === len) {\n\t\t\t\t\tlog.i(\"listener \" + str1 + \" not found for event \" + event);\n\t\t\t\t}\n\t\t\t\tif (listenersForEvent.length === 0) {\n\t\t\t\t\tdelete listeners[event];\n\t\t\t\t}\n\t\t\t} else {\n\t\t\t\t// no listener to remove was provided, so remove all listeners\n\t\t\t\t// for given event\n\t\t\t\tdelete listeners[event];\n\t\t\t}\n\t\t} else {\n\t\t\tlog.i(\"no listeners registered for event \" + event);\n\t\t}\n\t};\n\n\tmraid.resize = function() {\n\t\tlog.i(\"mraid.resize\");\n\t\t// The only time it is valid to call resize is when the ad is\n\t\t// a banner currently in either default or resized state.\n\t\t// Trigger an error if the current state is expanded.\n\t\tif (placementType === PLACEMENT_TYPES.INTERSTITIAL || state === STATES.LOADING || state === STATES.HIDDEN) {\n\t\t\t// do nothing\n\t\t\treturn;\n\t\t}\n\t\tif (state === STATES.EXPANDED) {\n\t\t\tmraid.fireErrorEvent(\"mraid.resize called when ad is in expanded state\", \"mraid.resize\");\n\t\t\treturn;\n\t\t}\n\t\tif (!isResizeReady) {\n\t\t\tmraid.fireErrorEvent(\"mraid.resize is not ready to be called\", \"mraid.resize\");\n\t\t\treturn;\n\t\t}\n\t\tcallNative(\"resize\");\n\t};\n\n\tmraid.setExpandProperties = function(properties) {\n\t\tlog.i(\"mraid.setExpandProperties\");\n\n\t\tif (!validate(properties, \"setExpandProperties\")) {\n\t\t\tlog.e(\"failed validation\");\n\t\t\treturn;\n\t\t}\n\n\t\tvar oldUseCustomClose = expandProperties.useCustomClose;\n\n\t\t// expandProperties contains 3 read-write properties: width, height, and useCustomClose;\n\t\t// the isModal property is read-only\n\t\tvar rwProps = [ \"width\", \"height\", \"useCustomClose\" ];\n\t\tfor (var i = 0; i < rwProps.length; i++) {\n\t\t\tvar propname = rwProps[i];\n\t\t\tif (properties.hasOwnProperty(propname)) {\n\t\t\t\texpandProperties[propname] = properties[propname];\n\t\t\t}\n\t\t}\n\n\t\t// In MRAID v2.0, all expanded ads by definition cover the entire screen,\n\t\t// so the only property that the native side has to know about is useCustomClose.\n\t\t// (That is, the width and height properties are not needed by the native code.)\n\t\tif (expandProperties.useCustomClose !== oldUseCustomClose) {\n\t\t\tcallNative(\"useCustomClose?useCustomClose=\"\t+ expandProperties.useCustomClose);\n\t\t}\n\n\t\tisExpandPropertiesSet = true;\n\t};\n\n\tmraid.setOrientationProperties = function(properties) {\n\t\tlog.i(\"mraid.setOrientationProperties\");\n\n\t\tif (!validate(properties, \"setOrientationProperties\")) {\n\t\t\tlog.e(\"failed validation\");\n\t\t\treturn;\n\t\t}\n\n\t\tvar newOrientationProperties = {};\n\t\tnewOrientationProperties.allowOrientationChange = orientationProperties.allowOrientationChange,\n\t\tnewOrientationProperties.forceOrientation = orientationProperties.forceOrientation;\n\n\t\t// orientationProperties contains 2 read-write properties:\n\t\t// allowOrientationChange and forceOrientation\n\t\tvar rwProps = [ \"allowOrientationChange\", \"forceOrientation\" ];\n\t\tfor (var i = 0; i < rwProps.length; i++) {\n\t\t\tvar propname = rwProps[i];\n\t\t\tif (properties.hasOwnProperty(propname)) {\n\t\t\t\tnewOrientationProperties[propname] = properties[propname];\n\t\t\t}\n\t\t}\n\n\t\t// Setting allowOrientationChange to true while setting forceOrientation\n\t\t// to either portrait or landscape\n\t\t// is considered an error condition.\n\t\tif (newOrientationProperties.allowOrientationChange\n\t\t\t\t&& newOrientationProperties.forceOrientation !== mraid.ORIENTATION_PROPERTIES_FORCE_ORIENTATION.NONE) {\n\t\t\tmraid.fireErrorEvent(\n\t\t\t\t\t\"allowOrientationChange is true but forceOrientation is \"\n\t\t\t\t\t+ newOrientationProperties.forceOrientation,\n\t\t\t\t\t\"setOrientationProperties\");\n\t\t\treturn;\n\t\t}\n\n\t\torientationProperties.allowOrientationChange = newOrientationProperties.allowOrientationChange;\n\t\torientationProperties.forceOrientation = newOrientationProperties.forceOrientation;\n\n\t\tvar params = \"allowOrientationChange=\"\n\t\t\t\t+ orientationProperties.allowOrientationChange\n\t\t\t\t+ \"&forceOrientation=\" + orientationProperties.forceOrientation;\n\n\t\tcallNative(\"setOrientationProperties?\" + params);\n\t};\n\n\tmraid.setResizeProperties = function(properties) {\n\t\tlog.i(\"mraid.setResizeProperties\");\n\n\t\tisResizeReady = false;\n\n\t\t// resizeProperties contains 6 read-write properties:\n\t\t// width, height, offsetX, offsetY, customClosePosition, allowOffscreen\n\n\t\t// The properties object passed into this function must contain width, height, offsetX, offsetY.\n\t\t// The remaining two properties are optional.\n\t\tvar requiredProps = [ \"width\", \"height\", \"offsetX\", \"offsetY\" ];\n\t\tfor (var i = 0; i < requiredProps.length; i++) {\n\t\t\tvar propname = requiredProps[i];\n\t\t\tif (!properties.hasOwnProperty(propname)) {\n\t\t\t\tmraid.fireErrorEvent(\n\t\t\t\t\t\t\"required property \" + propname + \" is missing\",\n\t\t\t\t\t\t\"mraid.setResizeProperties\");\n\t\t\t\treturn;\n\t\t\t}\n\t\t}\n\n\t\tif (!validate(properties, \"setResizeProperties\")) {\n\t\t\tmraid.fireErrorEvent(\"failed validation\", \"mraid.setResizeProperties\");\n\t\t\treturn;\n\t\t}\n\n        var adjustments = { \"x\": 0, \"y\": 0 };\n\n\t\tvar allowOffscreen = properties.hasOwnProperty(\"allowOffscreen\") ? properties.allowOffscreen : resizeProperties.allowOffscreen;\n        if (!allowOffscreen) {\n            if (properties.width > maxSize.width || properties.height > maxSize.height) {\n                mraid.fireErrorEvent(\"resize width or height is greater than the maxSize width or height\", \"mraid.setResizeProperties\");\n                return;\n            }\n            adjustments = fitResizeViewOnScreen(properties);\n        } else if (!isCloseRegionOnScreen(properties)) {\n            mraid.fireErrorEvent(\"close event region will not appear entirely onscreen\", \"mraid.setResizeProperties\");\n            return;\n        }\n\n\t\tvar rwProps = [ \"width\", \"height\", \"offsetX\", \"offsetY\", \"customClosePosition\", \"allowOffscreen\" ];\n\t\tfor (var i = 0; i < rwProps.length; i++) {\n\t\t\tvar propname = rwProps[i];\n\t\t\tif (properties.hasOwnProperty(propname)) {\n\t\t\t\tresizeProperties[propname] = properties[propname];\n\t\t\t}\n\t\t}\n\n\t\tvar params =\n\t\t\t\"width=\" + resizeProperties.width +\n\t\t\t\"&height=\" + resizeProperties.height +\n\t        \"&offsetX=\" + (resizeProperties.offsetX + adjustments.x) +\n\t        \"&offsetY=\" + (resizeProperties.offsetY + adjustments.y) +\n\t\t\t\"&customClosePosition=\" + resizeProperties.customClosePosition +\n\t\t\t\"&allowOffscreen=\" + resizeProperties.allowOffscreen;\n\n\t\tcallNative(\"setResizeProperties?\" + params);\n\n\t\tisResizeReady = true;\n\t};\n\n\tmraid.storePicture = function(url) {\n\t\tlog.i(\"mraid.storePicture \" + url);\n\t\tif (supportedFeatures[mraid.SUPPORTED_FEATURES.STOREPICTURE]) {\n\t\t\tcallNative(\"storePicture?url=\" + encodeURIComponent(url));\n\t\t} else {\n\t\t\tlog.e(\"storePicture is not supported\");\n\t\t}\n\t};\n\n\tmraid.supports = function(feature) {\n\t\tlog.i(\"mraid.supports \" + feature + \" \" + supportedFeatures[feature]);\n\t\tvar retval = supportedFeatures[feature];\n\t\tif (typeof retval === \"undefined\") {\n\t\t\tretval = false;\n\t\t}\n\t\treturn retval;\n\t};\n\n\tmraid.useCustomClose = function(isCustomClose) {\n\t\tlog.i(\"mraid.useCustomClose \" + isCustomClose);\n\t\tif (expandProperties.useCustomClose !== isCustomClose) {\n\t\t\texpandProperties.useCustomClose = isCustomClose;\n\t\t\tcallNative(\"useCustomClose?useCustomClose=\"\n\t\t\t\t\t+ expandProperties.useCustomClose);\n\t\t}\n\t};\n\n\tmraid.rewardComplete = function() {\n\t\tlog.i(\"mraid.rewardComplete\");\n\t\tcallNative(\"rewardComplete\");\n\t}\n\n\t/***************************************************************************\n\t * helper methods called by SDK\n\t **************************************************************************/\n\n\t// setters to change state\n\tmraid.setCurrentPosition = function(x, y, width, height) {\n\t\tlog.i(\"mraid.setCurrentPosition \" + x + \",\" + y + \",\" + width + \",\"\t+ height);\n\n\t\tvar previousSize = {};\n\t\tpreviousSize.width = currentPosition.width;\n\t\tpreviousSize.height = currentPosition.height;\n\t\tlog.i(\"previousSize \" + previousSize.width + \",\" + previousSize.height);\n\n\t\tcurrentPosition.x = x;\n\t\tcurrentPosition.y = y;\n\t\tcurrentPosition.width = width;\n\t\tcurrentPosition.height = height;\n\n\t\tif (width !== previousSize.width || height !== previousSize.height) {\n\t\t\tmraid.fireSizeChangeEvent(width, height);\n\t\t}\n\t};\n\n\tmraid.setDefaultPosition = function(x, y, width, height) {\n\t\tlog.i(\"mraid.setDefaultPosition \" + x + \",\" + y + \",\" + width + \",\"\t+ height);\n\t\tdefaultPosition.x = x;\n\t\tdefaultPosition.y = y;\n\t\tdefaultPosition.width = width;\n\t\tdefaultPosition.height = height;\n\t};\n\n\tmraid.setExpandSize = function(width, height) {\n\t\tlog.i(\"mraid.setExpandSize \" + width + \"x\" + height);\n\t\texpandProperties.width = width;\n\t\texpandProperties.height = height;\n\t};\n\n\tmraid.setMaxSize = function(width, height) {\n\t\tlog.i(\"mraid.setMaxSize \" + width + \"x\" + height);\n\t\tmaxSize.width = width;\n\t\tmaxSize.height = height;\n\t};\n\n\tmraid.setPlacementType = function(pt) {\n\t\tlog.i(\"mraid.setPlacementType \" + pt);\n\t\tplacementType = pt;\n\t};\n\n\tmraid.setScreenSize = function(width, height) {\n\t\tlog.i(\"mraid.setScreenSize \" + width + \"x\" + height);\n\t\tscreenSize.width = width;\n\t\tscreenSize.height = height;\n\t\tif (!isExpandPropertiesSet) {\n\t\t\texpandProperties.width = width;\n\t\t\texpandProperties.height = height;;\n\t\t}\n\t};\n\n\tmraid.setSupports = function(feature, supported) {\n\t\tlog.i(\"mraid.setSupports \" + feature + \" \" + supported);\n\t\tsupportedFeatures[feature] = supported;\n\t};\n\n\t// methods to fire events\n\n\tmraid.fireErrorEvent = function(message, action) {\n\t\tlog.i(\"mraid.fireErrorEvent \" + message + \" \" + action);\n\t\tfireEvent(mraid.EVENTS.ERROR, message, action);\n\t};\n\n\tmraid.fireReadyEvent = function() {\n\t\tlog.i(\"mraid.fireReadyEvent\");\n\t\tfireEvent(mraid.EVENTS.READY);\n\t};\n\n\tmraid.fireSizeChangeEvent = function(width, height) {\n\t\tlog.i(\"mraid.fireSizeChangeEvent \" + width + \"x\" + height);\n\t\tif (state !== mraid.STATES.LOADING) {\n\t\t\tfireEvent(mraid.EVENTS.SIZECHANGE, width, height);\n\t\t}\n\t};\n\n\tmraid.fireStateChangeEvent = function(newState) {\n\t\tlog.i(\"mraid.fireStateChangeEvent \" + newState);\n\t\tif (state !== newState) {\n\t\t\tstate = newState;\n\t\t\tfireEvent(mraid.EVENTS.STATECHANGE, state);\n\t\t}\n\t};\n\n\tmraid.fireViewableChangeEvent = function(newIsViewable) {\n\t\tlog.i(\"mraid.fireViewableChangeEvent \" + newIsViewable);\n\t\tif (isViewable !== newIsViewable) {\n\t\t\tisViewable = newIsViewable;\n\t\t\tfireEvent(mraid.EVENTS.VIEWABLECHANGE, isViewable);\n\t\t}\n\t};\n\n\t/***************************************************************************\n\t * internal helper methods\n\t **************************************************************************/\n\n\tfunction callNative(command) {\n\t\tvar iframe = document.createElement(\"IFRAME\");\n\t\tiframe.setAttribute(\"src\", \"mraid://\" + command);\n\t\tdocument.documentElement.appendChild(iframe);\n\t\tiframe.parentNode.removeChild(iframe);\n\t\tiframe = null;\n\t};\n\n\tfunction fireEvent(event) {\n\t\tvar args = Array.prototype.slice.call(arguments);\n\t\targs.shift();\n\t\tlog.i(\"fireEvent \" + event + \" [\" + args.toString() + \"]\");\n\t\tvar eventListeners = listeners[event];\n\t\tif (eventListeners) {\n\t\t\tvar len = eventListeners.length;\n\t\t\tlog.i(len + \" listener(s) found\");\n\t\t\tfor (var i = 0; i < len; i++) {\n\t\t\t\teventListeners[i].apply(null, args);\n\t\t\t}\n\t\t} else {\n\t\t\tlog.i(\"no listeners found\");\n\t\t}\n\t};\n\n\tfunction contains(value, array) {\n\t\tfor ( var i in array) {\n\t\t\tif (array[i] === value) {\n\t\t\t\treturn true;\n\t\t\t}\n\t\t}\n\t\treturn false;\n\t};\n\n\tmnRewardUser = function(mnType, mnValue) {\n\t\tlog.i(\"mnRewardUser\");\n\t\tmraid.rewardComplete();\n\t};\n\n\t// The action parameter is a string which is the name of the setter function\n\t// which called this function\n\t// (in other words, setExpandPropeties, setOrientationProperties, or\n\t// setResizeProperties).\n\t// It serves both as the key to get the the appropriate set of validating\n\t// functions from the allValidators object\n\t// as well as the action parameter of any error event that may be thrown.\n\tfunction validate(properties, action) {\n\t\tvar retval = true;\n\t\tvar validators = allValidators[action];\n\t\tfor (var prop in properties) {\n\t\t\tvar validator = validators[prop];\n\t\t\tvar value = properties[prop];\n\t\t\tif (validator && !validator(value)) {\n\t\t\t\tmraid.fireErrorEvent(\"Value of property \" + prop + \" (\" + value\t+ \") is invalid\", \"mraid.\" + action);\n\t\t\t\tretval = false;\n\t\t\t}\n\t\t}\n\t\treturn retval;\n\t};\n\n\tvar allValidators = {\n\t\t\"setExpandProperties\" : {\n\t\t\t// In MRAID 2.0, the only property in expandProperties we actually care about is useCustomClose.\n\t\t\t// Still, we'll do a basic sanity check on the width and height properties, too.\n\t\t\t\"width\" : function(width) {\n\t\t\t\treturn !isNaN(width);\n\t\t\t},\n\t\t\t\"height\" : function(height) {\n\t\t\t\treturn !isNaN(height);\n\t\t\t},\n\t\t\t\"useCustomClose\" : function(useCustomClose) {\n\t\t\t\treturn (typeof useCustomClose === \"boolean\");\n\t\t\t}\n\t\t},\n\t\t\"setOrientationProperties\" : {\n\t\t\t\"allowOrientationChange\" : function(allowOrientationChange) {\n\t\t\t\treturn (typeof allowOrientationChange === \"boolean\");\n\t\t\t},\n\t\t\t\"forceOrientation\" : function(forceOrientation) {\n\t\t\t\tvar validValues = [ \"portrait\", \"landscape\", \"none\" ];\n\t\t\t\treturn (typeof forceOrientation === \"string\" && validValues.indexOf(forceOrientation) !== -1);\n\t\t\t}\n\t\t},\n\t\t\"setResizeProperties\" : {\n\t\t\t\"width\" : function(width) {\n\t\t\t\treturn !isNaN(width) && 50 <= width;\n\t\t\t},\n\t\t\t\"height\" : function(height) {\n\t\t\t\treturn !isNaN(height) && 50 <= height;\n\t\t\t},\n\t\t\t\"offsetX\" : function(offsetX) {\n\t\t\t\treturn !isNaN(offsetX);\n\t\t\t},\n\t\t\t\"offsetY\" : function(offsetY) {\n\t\t\t\treturn !isNaN(offsetY);\n\t\t\t},\n\t\t\t\"customClosePosition\" : function(customClosePosition) {\n\t\t\t\tvar validPositions = [ \"top-left\", \"top-center\", \"top-right\",\n\t\t\t\t                       \"center\",\n\t\t\t\t                       \"bottom-left\", \"bottom-center\",\t\"bottom-right\" ];\n\t\t\t\treturn (typeof customClosePosition === \"string\" && validPositions.indexOf(customClosePosition) !== -1);\n\t\t\t},\n\t\t\t\"allowOffscreen\" : function(allowOffscreen) {\n\t\t\t\treturn (typeof allowOffscreen === \"boolean\");\n\t\t\t}\n\t\t}\n\t};\n\n    function isCloseRegionOnScreen(properties) {\n        log.d(\"isCloseRegionOnScreen\");\n        log.d(\"defaultPosition \" + defaultPosition.x + \" \" + defaultPosition.y);\n        log.d(\"offset \" + properties.offsetX + \" \" + properties.offsetY);\n\n        var resizeRect = {};\n        resizeRect.x = defaultPosition.x + properties.offsetX;\n        resizeRect.y = defaultPosition.y + properties.offsetY;\n        resizeRect.width = properties.width;\n        resizeRect.height = properties.height;\n        printRect(\"resizeRect\", resizeRect);\n\n\t\tvar customClosePosition = properties.hasOwnProperty(\"customClosePosition\") ?\n\t\t\t\tproperties.customClosePosition : resizeProperties.customClosePosition;\n        log.d(\"customClosePosition \" + customClosePosition);\n\n        var closeRect = { \"width\": 50, \"height\": 50 };\n\n        if (customClosePosition.search(\"left\") !== -1) {\n            closeRect.x = resizeRect.x;\n        } else if (customClosePosition.search(\"center\") !== -1) {\n            closeRect.x = resizeRect.x + (resizeRect.width / 2) - 25;\n        } else if (customClosePosition.search(\"right\") !== -1) {\n            closeRect.x = resizeRect.x + resizeRect.width - 50;\n        }\n\n        if (customClosePosition.search(\"top\") !== -1) {\n            closeRect.y = resizeRect.y;\n        } else if (customClosePosition === \"center\") {\n            closeRect.y = resizeRect.y + (resizeRect.height / 2) - 25;\n        } else if (customClosePosition.search(\"bottom\") !== -1) {\n            closeRect.y = resizeRect.y + resizeRect.height - 50;\n        }\n\n        var maxRect = { \"x\": 0, \"y\": 0 };\n        maxRect.width = maxSize.width;\n        maxRect.height = maxSize.height;\n\n        return isRectContained(maxRect, closeRect);\n    }\n\n    function fitResizeViewOnScreen(properties) {\n        log.d(\"fitResizeViewOnScreen\");\n        log.d(\"defaultPosition \" + defaultPosition.x + \" \" + defaultPosition.y);\n        log.d(\"offset \" + properties.offsetX + \" \" + properties.offsetY);\n\n        var resizeRect = {};\n        resizeRect.x = defaultPosition.x + properties.offsetX;\n        resizeRect.y = defaultPosition.y + properties.offsetY;\n        resizeRect.width = properties.width;\n        resizeRect.height = properties.height;\n        printRect(\"resizeRect\", resizeRect);\n\n        var maxRect = { \"x\": 0, \"y\": 0 };\n        maxRect.width = maxSize.width;\n        maxRect.height = maxSize.height;\n\n        var adjustments = { \"x\": 0, \"y\": 0 };\n\n        if (isRectContained(maxRect, resizeRect)) {\n            log.d(\"no adjustment necessary\");\n            return adjustments;\n        }\n\n        if (resizeRect.x < maxRect.x) {\n            adjustments.x = maxRect.x - resizeRect.x;\n        } else if ((resizeRect.x + resizeRect.width) > (maxRect.x + maxRect.width)) {\n            adjustments.x = (maxRect.x + maxRect.width) - (resizeRect.x + resizeRect.width);\n        }\n        log.d(\"adjustments.x \" + adjustments.x);\n\n        if (resizeRect.y < maxRect.y) {\n            adjustments.y = maxRect.y - resizeRect.y;\n        } else if ((resizeRect.y + resizeRect.height) > (maxRect.y + maxRect.height)) {\n            adjustments.y = (maxRect.y + maxRect.height) - (resizeRect.y + resizeRect.height);\n        }\n        log.d(\"adjustments.y \" + adjustments.y);\n\n        resizeRect.x = defaultPosition.x + properties.offsetX + adjustments.x;\n        resizeRect.y = defaultPosition.y + properties.offsetY + adjustments.y;\n        printRect(\"adjusted resizeRect\", resizeRect);\n\n        return adjustments;\n    }\n\n    function isRectContained(containingRect, containedRect) {\n        log.d(\"isRectContained\");\n        printRect(\"containingRect\", containingRect);\n        printRect(\"containedRect\", containedRect);\n        return (containedRect.x >= containingRect.x &&\n            (containedRect.x + containedRect.width) <= (containingRect.x + containingRect.width) &&\n            containedRect.y >= containingRect.y &&\n            (containedRect.y + containedRect.height) <= (containingRect.y + containingRect.height));\n    }\n\n    function printRect(label, rect) {\n        log.d(label +\n            \" [\" + rect.x + \",\" + rect.y + \"]\" +\n            \",[\" + (rect.x + rect.width) + \",\" + (rect.y + rect.height) + \"]\" +\n            \" (\" + rect.width + \"x\" + rect.height + \")\");\n    }\n\n\tmraid.dumpListeners = function() {\n\t\tvar nEvents = Object.keys(listeners).length;\n\t\tlog.i(\"dumping listeners (\" + nEvents + \" events)\");\n\t\tfor ( var event in listeners) {\n\t\t\tvar eventListeners = listeners[event];\n\t\t\tlog.i(\"  \" + event + \" contains \" + eventListeners.length + \" listeners\");\n\t\t\tfor (var i = 0; i < eventListeners.length; i++) {\n\t\t\t\tlog.i(\"    \" + eventListeners[i]);\n\t\t\t}\n\t\t}\n\t};\nmraid.success = function()\n{\nlog.i(\"mraid.success\");\n    if (state === STATES.LOADING ||\n        (state === STATES.DEFAULT && placementType === PLACEMENT_TYPES.INLINE) ||\n        state === STATES.HIDDEN) {\n        // do nothing\n        return;\n    }\n    callNative(\"success\");\n}\nmraid.replay = function()\n{\nlog.i(\"mraid.replay\");\ncallNative(\"replay\");\n};\n\tconsole.log(\"MRAID object loaded\");\n\n})();\n</script>";

    public static String getMraid() {
        StringBuilder buf = new StringBuilder();
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(MRaidJS.class.getResourceAsStream("/assets/files/MRaidJS"), "UTF-8"));
            boolean isFirst = true;
            while (true) {
                String str = in.readLine();
                if (str == null) {
                    break;
                }
                if (isFirst) {
                    isFirst = false;
                } else {
                    buf.append('\n');
                }
                buf.append(str);
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buf.toString();
    }
}
