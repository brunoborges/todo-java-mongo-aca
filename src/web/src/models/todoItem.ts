export enum TodoItemState {
    Todo = "TODO",
    InProgress = "INPROGRESS",
    Done = "DONE"
}

export interface TodoItem {
    id?: string
    listId: string
    name: string
    state: TodoItemState
    description?: string
    dueDate?: Date
    completedDate?:Date
    createdDate?: Date
    updatedDate?: Date
}