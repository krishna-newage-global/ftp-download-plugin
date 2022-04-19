export interface FTPDownloadPlugin {
  echo(options: { value: string }): Promise<{ value: string }>;
}
