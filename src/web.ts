import { WebPlugin } from '@capacitor/core';

import type { FTPDownloadPlugin ,DownloadFileParams } from './definitions';

export class FTPDownloadWeb extends WebPlugin implements FTPDownloadPlugin {
  async echo(options: { value: string }): Promise<{ value: string }> {
    console.log('ECHO', options);
    return options;
  }

  async requestPermissions(): Promise<{ value: string }> {
    return { value: "" };
  }

  async doanloadFTPFile(params: DownloadFileParams): Promise<{ value: string }> {
    console.log('doanloadFTPFile', params);
    return { value: "" };
  }
}
