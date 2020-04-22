export default class APIError extends Error {
  constructor(message) {
    super(message || `Algo deu errado.`);
  }
}
