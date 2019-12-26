declare module "@capacitor/core" {
  interface PluginRegistry {
    CapacitorFirebasex: CapacitorFirebasexPlugin;
  }
}

export interface CapacitorFirebasexPlugin {
  echo(options: { value: string }): Promise<{value: string}>;
}
