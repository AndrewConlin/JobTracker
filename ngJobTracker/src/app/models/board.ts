export class Board {
  id: number;
  title: string;
  description: string;
  createDate: Date;

  constructor(title?: string, description?: string, createDate?: Date) {
    this.title = title;
    this.description = description;
    this.createDate = createDate;
  }
}
