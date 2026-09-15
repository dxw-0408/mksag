import request from './request'

export interface LibraryHours {
  open: string
  close: string
  note?: string
}

export async function getLibraryHours(): Promise<LibraryHours> {
  const response = await request.get<LibraryHours>('/tools/library-hours')
  return response.data
}
