import axios from 'axios';
import Dragon from '../models/Dragon';
import DragonList from '../models/DragonList';
import APIError from '../errors/APIError';

export default class DragonService {
  static findAll() {
    return axios
      .get(this.getUrl('dragon'))
      .then((result) => {
        return new DragonList(
          result.data.map((drag) => {
            return new Dragon(
              drag.id,
              drag.createdAt,
              drag.name,
              drag.type,
              drag.histories,
              drag.image
            );
          })
        );
      })
      .catch(({ response }) => {
        throw new APIError(response.data);
      });
  }

  static find(id) {
    return axios
      .get(this.getUrl(`dragon/${id}`))
      .then((result) => {
        return new Dragon(
          result.data.id,
          result.data.createdAt,
          result.data.name,
          result.data.type,
          result.data.histories.toString(),
          result.data.image
        );
      })
      .catch(({ response }) => {
        throw new APIError(response.data);
      });
  }

  static create(name, type, histories, image) {
    return axios
      .post(this.getUrl('dragon'), {
        name,
        type,
        histories,
        image,
      })
      .catch(({ response }) => {
        throw new APIError(response.data);
      });
  }

  static update(id, name, type, histories, image) {
    return axios
      .put(this.getUrl(`dragon/${id}`), {
        name,
        type,
        histories,
        image,
      })
      .catch(({ response }) => {
        throw new APIError(response.data);
      });
  }

  static delete(id) {
    return axios.delete(this.getUrl(`dragon/${id}`)).catch(({ response }) => {
      throw new APIError(response.data);
    });
  }

  static getUrl(path) {
    return `${process.env.REACT_APP_API_URL}/${path}`;
  }
}
