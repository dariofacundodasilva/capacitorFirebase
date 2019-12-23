import Foundation
import Capacitor

/**
 * Please read the Capacitor iOS Plugin Development Guide
 * here: https://capacitor.ionicframework.com/docs/plugins/ios
 */
@objc(CapacitorFirebasePlugin)
public class CapacitorFirebasePlugin: CAPPlugin {
    
    @objc func echo(_ call: CAPPluginCall) {
        let value = call.getString("value") ?? ""
        call.success([
            "value": value
        ])
    }

    @objc func initializeSecondaryAppFirebase(_ call: CAPPluginCall) {
        CapacitorFirebaseBridge.initializeSecondaryAppFirebase();
        call.success();
    }

    @objc func getApps(_ call: CAPPluginCall){
        print("----logs inicio---");
        print(FirebaseApp.allApps);
        print("----logs fin---");
        call.success();
    }
}
