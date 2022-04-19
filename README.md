# ftp-download-plugin

Plugin to download FTP files

## Install

```bash
npm install ftp-download-plugin
npx cap sync
```

## API

<docgen-index>

* [`echo(...)`](#echo)
* [`doanloadFTPFile(...)`](#doanloadftpfile)
* [Interfaces](#interfaces)

</docgen-index>

<docgen-api>
<!--Update the source file JSDoc comments and rerun docgen to update the docs below-->

### echo(...)

```typescript
echo(options: { value: string; }) => Promise<{ value: string; }>
```

| Param         | Type                            |
| ------------- | ------------------------------- |
| **`options`** | <code>{ value: string; }</code> |

**Returns:** <code>Promise&lt;{ value: string; }&gt;</code>

--------------------


### doanloadFTPFile(...)

```typescript
doanloadFTPFile(params: DownloadFileParams) => Promise<{ value: string; }>
```

| Param        | Type                                                              |
| ------------ | ----------------------------------------------------------------- |
| **`params`** | <code><a href="#downloadfileparams">DownloadFileParams</a></code> |

**Returns:** <code>Promise&lt;{ value: string; }&gt;</code>

--------------------


### Interfaces


#### DownloadFileParams

| Prop                    | Type                |
| ----------------------- | ------------------- |
| **`ftpURL`**            | <code>string</code> |
| **`ftpUserName`**       | <code>string</code> |
| **`ftpPassword`**       | <code>string</code> |
| **`filePath`**          | <code>string</code> |
| **`fileName`**          | <code>string</code> |
| **`storageFolderName`** | <code>string</code> |

</docgen-api>
