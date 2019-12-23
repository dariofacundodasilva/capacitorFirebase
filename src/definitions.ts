declare module "@capacitor/core" {
  interface PluginRegistry {
    CapacitorFirebasePlugin: CapacitorFirebasePluginPlugin;
  }
}

export interface CapacitorFirebasePluginPlugin {
  echo(options: { value: string }): Promise<{value: string}>;
  initializeSecondaryAppFirebase(options: { value: string }): Promise<{value: string}>;
  getApps(options: {}): Promise<{value: string}>;
}

