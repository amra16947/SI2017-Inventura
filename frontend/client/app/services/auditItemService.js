import BaseService from './baseService';

export default class AuditItemService extends BaseService {
    setPresent(id, present) {
        return super.post('auditItems/' + id + '/set-present', {
            present
        });
    }

    setSkuCorrect(id, skuCorrect) {
        return super.post('auditItems/' + id + '/set-sku-correct', {
            skuCorrect
        });
    }

    setStatus(id, statusId) {
        return super.post('auditItems/' + id + '/set-status', {
            statusId
        });
    }
}