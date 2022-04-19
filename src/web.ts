import { WebPlugin } from '@capacitor/core';

import type { FTPDownloadPlugin } from './definitions';

export class FTPDownloadWeb extends WebPlugin implements FTPDownloadPlugin {
  async echo(options: { value: string }): Promise<{ value: string }> {
    console.log('ECHO', options);
    return options;
  }
}
