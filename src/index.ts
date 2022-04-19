import { registerPlugin } from '@capacitor/core';

import type { FTPDownloadPlugin } from './definitions';

const FTPDownload = registerPlugin<FTPDownloadPlugin>('FTPDownload', {
  web: () => import('./web').then(m => new m.FTPDownloadWeb()),
});

export * from './definitions';
export { FTPDownload };
