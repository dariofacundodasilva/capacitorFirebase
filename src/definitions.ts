declare module "@capacitor/core" {
  interface PluginRegistry {
    CapacitorFirebasePlugin: CapacitorFirebasePluginInterface;
  }
}

export interface CapacitorFirebasePluginInterface {
  echo(options: { value: string }): Promise<{value: string}>;
  initializeSecondaryAppFirebase(options: { value: string }): Promise<{value: string}>;
  getApps(options: {}): Promise<{value: string}>;
}

