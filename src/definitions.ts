export interface FTPDownloadPlugin {
  echo(options: { value: string }): Promise<{ value: string }>;
  doanloadFTPFile(params: DownloadFileParams): Promise<{ value: string }>
}

export interface DownloadFileParams {
   ftpURL: string;
   ftpUserName: string;
   ftpPassword: string;
   filePath: string;
   fileName: string;
   storageFolderName: string;
}